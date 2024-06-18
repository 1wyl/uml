package com.example.backend.oldchat;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SignatureUtil {

    private static final Logger log = LoggerFactory.getLogger(SignatureUtil.class);

    @Value("${api.bluelm.app-id}")
    private String appId;

    @Value("${api.bluelm.secret}")
    private String appKey;

    public Map<String, String> generateAuthHeaders(String method, String uri, Map<String, String> queryParams) {
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        // timestamp = "1629255133";
        String nonce = generateNonce(8);
        // nonce = "le1qqjex" ;
        String canonicalQueryString = genCanonicalQueryString(queryParams);
        String signedHeadersString = String.format("x-ai-gateway-app-id:%s\nx-ai-gateway-timestamp:%s\nx-ai-gateway-nonce:%s", appId, timestamp, nonce);
        //System.out.println("===============");
        //System.out.println(signedHeadersString);
        String signingString = buildSigningString(method, uri, canonicalQueryString, appId, timestamp, nonce, signedHeadersString);
        //System.out.println(signingString);
        String signature = generateSignature(appKey, signingString);
        //System.out.println(signature);



        Map<String, String> headers = new HashMap<>();
        headers.put("X-AI-GATEWAY-APP-ID", appId);
        headers.put("X-AI-GATEWAY-TIMESTAMP", timestamp);
        headers.put("X-AI-GATEWAY-NONCE", nonce);
        headers.put("X-AI-GATEWAY-SIGNED-HEADERS", "x-ai-gateway-app-id;x-ai-gateway-timestamp;x-ai-gateway-nonce");
        headers.put("X-AI-GATEWAY-SIGNATURE", signature);

        return headers;
    }

    private String generateNonce(int length) {
        SecureRandom random = new SecureRandom();
        byte[] nonceBytes = new byte[length];
        random.nextBytes(nonceBytes);
        return Base64.encodeBase64URLSafeString(nonceBytes);
    }

    private String genCanonicalQueryString(Map<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return "";
        }
        return queryParams.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    private String encode(String data) {
        try {
            return URLEncoder.encode(data, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding parameter", e);
        }
    }

    private String buildSigningString(String method, String uri, String canonicalQuery, String appId, String timestamp, String nonce, String signedHeaders) {
        return String.join("\n", method, uri, canonicalQuery, appId, timestamp, signedHeaders);
    }

    private String generateSignature(String secret, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            return Base64.encodeBase64String(mac.doFinal(data.getBytes()));
        } catch (Exception e) {
            log.error("Failed to calculate HMAC", e);
            throw new RuntimeException("Failed to calculate HMAC", e);
        }
    }
}
