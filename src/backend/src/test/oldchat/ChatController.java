package com.example.backend.oldchat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private WebClient webClient;
    @Autowired
    private SignatureUtil signatureUtil;
    @Autowired
    private ChatMapper chatMapper;

    @PostMapping("/send")
    public Mono<ResponseEntity<String>> sendMessage(@RequestBody ChatMessage message) {
        chatMapper.save(message);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("requestId", UUID.randomUUID().toString());  // 生成唯一的requestId
        //queryParams.put("requestId", "1e344557-8e8b-43e3-a36e-94e7f36616e0");
        // 创建一个MultiValueMap来存储转换后的参数
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        queryParams.forEach((key, value) -> multiValueMap.add(key, value));
        Mono<ResponseEntity<String>> response =
                webClient.post()
                        .uri(uriBuilder -> uriBuilder.path("/vivogpt/completions")
                                .queryParams(multiValueMap)  // 使用MultiValueMap作为查询参数
                                .build())
                        .headers(headers -> headers.setAll(signatureUtil.generateAuthHeaders("POST", "/vivogpt/completions", queryParams)))
                        .bodyValue(buildRequestBody(message))
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(body -> ResponseEntity.ok().body(body))
                        .onErrorResume(e -> {
                            e.printStackTrace();  // 在实际部署中应考虑更合适的错误处理方式
                            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing your request: " + e.getMessage()));
                        });
        //System.out.println(response.block());
        return response;
    }

    // 确保当构建JSON字符串时包括sessionId
    private String buildRequestBody(ChatMessage message) {
        return String.format("{\"prompt\": \"%s\", \"model\": \"vivo-BlueLM-TB\", \"sessionId\": \"%s\"}",
                message.getContent(),
                message.getSessionid()); // 使用传递的sessionId
    }
}
