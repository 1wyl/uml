package com.example.backend.source;


import com.example.backend.CommonResult;
import com.example.backend.Event;
import com.example.backend.Request;
import com.example.backend.user.User;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/source")
public class SourceController {

    @Value("${file.fod-dir}")
    private String foodDir;
    @Value("${file.med-dir}")
    private String medDir;

    @Autowired
    private UserService userService;
    @Autowired
    private SourceMapper sourceMapper;



    @PostMapping("/uploadMedicine")
    public CommonResult<?> uploadImage(@RequestHeader("Authorization") String accessToken,
                                       @RequestParam("files") MultipartFile[] files,
                                       @RequestParam("description") String description
    ) throws Exception {
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);
        for (MultipartFile file : files) {
            try {
                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                System.out.println("文件名为" + uniqueFileName);
                File uploadedFile = new File(medDir + uniqueFileName);
                file.transferTo(uploadedFile);

                Medicine medicine = new Medicine();
                medicine.setDescription(description);
                medicine.setDate(new Date());
                medicine.setUrl(uniqueFileName);
                sourceMapper.addMedicine(medicine);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        return CommonResult.success(200);
    }

    @PostMapping("/requestfood")
    public CommonResult<String> requestFood(@RequestHeader("Authorization") String accessToken, @RequestBody int fid) throws Exception {
        // Check authorization
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);
        // Perform request food operation
        Request request = new Request();
        request.setRequestTime(LocalDateTime.now());
        request.setName(user.getName());
        request.setAddress(user.getAddress());
        request.setPhone(user.getPhone());
        request.setServiceType("食品");
        request.setProductName(sourceMapper.getFoodName(fid));
        // Populate other request fields as needed
        sourceMapper.addRequest(request);

        return CommonResult.success("Food requested successfully");
    }
    @PostMapping("/emer")
    public CommonResult<String> emer(@RequestHeader("Authorization") String accessToken, @RequestBody String location) throws Exception {
        // Check authorization
        System.out.println(location);
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);

        if(user.getRole()!=1)
            return CommonResult.error(400,"身份验证失败");
        Event event = new Event();
        event.setTime(LocalDateTime.now());
        event.setLocation(location);
        event.setAddress(user.getAddress());
        event.setName(user.getName());
        event.setPhone(user.getPhone());
        sourceMapper.addEvent(event);

        return CommonResult.success("Event requested successfully");
    }

    @GetMapping("/fetchemer")
    public CommonResult<?> fetchemer(@RequestHeader("Authorization") String accessToken) throws Exception {
        // Check authorization
        System.out.println("start");
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);

        if(user.getRole()!=2)
            return CommonResult.error(400,"身份验证失败");

        List<Event> events = sourceMapper.fetchEvent();

        return CommonResult.success(events);
    }
    @PostMapping("/requestmedicine")
    public CommonResult<String> requestMedicine(@RequestHeader("Authorization") String accessToken, @RequestParam int mid) throws Exception {
       String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);
        // Perform request food operation
        Request request = new Request();
        request.setRequestTime(LocalDateTime.now());
        request.setName(user.getName());
        request.setAddress(user.getAddress());
        request.setPhone(user.getPhone());
        request.setServiceType("药品");
        request.setProductName(sourceMapper.getMedicineName(mid));
        // Populate other request fields as needed
        sourceMapper.addRequest(request);

        return CommonResult.success("Food requested successfully");
    }
    @PostMapping("/uploadFood")
    public CommonResult<?> uploadMedicine(@RequestHeader("Authorization") String accessToken,
                                       @RequestParam("files") MultipartFile[] files,
                                       @RequestParam("description") String description
    ) throws Exception {
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);
        for (MultipartFile file : files) {
            try {
                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                System.out.println("文件名为" + uniqueFileName);
                File uploadedFile = new File(foodDir + uniqueFileName);
                file.transferTo(uploadedFile);

                Food food = new Food();
                food.setDescription(description);
                food.setDate(new Date());
                food.setUrl(uniqueFileName);
                sourceMapper.addFood(food);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        return CommonResult.success(200);
    }


    @GetMapping("/food")
    public CommonResult<?> getImages(@RequestHeader("Authorization") String accessToken) throws Exception {
        System.out.println("start");
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);
        List<Map<String,String>> pics = sourceMapper.getAllPictures();
        return CommonResult.success(pics);
    }

    @GetMapping("/medicine")
    public CommonResult<?> getVideos(@RequestHeader("Authorization") String accessToken) throws Exception {
        String token = accessToken.substring(7);
        User user = userService.getUserInfoByToken(token);
        List<Map<String,String>> meds = sourceMapper.getAllMeds();
//        System.out.println(urls);
        return CommonResult.success(meds);
    }

}

