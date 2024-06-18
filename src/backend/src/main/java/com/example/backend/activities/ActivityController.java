package com.example.backend.activities;

import com.example.backend.CommonResult;
import com.example.backend.task.Task;
import com.example.backend.user.User;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    // 获取活动列表
    @GetMapping("/get")
    public CommonResult<?> getActivities(@RequestHeader("Authorization") String ACCESS_TOKEN) {
        try {
            String token = ACCESS_TOKEN.substring(7);
            User userInfo = userService.getUserInfoByToken(token);
            if(userInfo!=null){
                List<Activity> activities = activityService.getAllActivities();
                return CommonResult.success(activities);
            }
            else {
                return CommonResult.error(401,"未找到用户");
            }
        } catch (Exception e) {
            return CommonResult.error(400,"身份识别出现错误");
        }
    }
    @PostMapping("/add")
    public CommonResult<?> addTask(@RequestBody Activity activity, @RequestHeader("Authorization") String ACCESS_TOKEN) {
        try {
            String token = ACCESS_TOKEN.substring(7);
            // 可能需要从 token 解析用户 ID
            User user = userService.getUserInfoByToken(token);
            if(user.getRole()==2){
                System.out.println(activity.getStartTime());
                activity.setUploadTime(LocalDateTime.now());
                activityService.addActivity(activity);
                return CommonResult.success("插入成功");
            }
//            task.setUid(user.getUid());  // 设置任务的用户 ID
//            //System.out.println(task.getUid());
//            activityService.addTask(task);
//            return ResponseEntity.ok("任务添加成功");
            else{
                return CommonResult.error(400,"身份认证失败");
            }
        } catch (Exception e) {
            return CommonResult.error(400,"任务添加出现错误");
        }
    }
    // 删除活动
    @PostMapping("/delete")
    public CommonResult<?> deleteActivity(@RequestBody Activity activity, @RequestHeader("Authorization") String ACCESS_TOKEN) {
        try {
            String token = ACCESS_TOKEN.substring(7);
            User userInfo = userService.getUserInfoByToken(token);
            if(userInfo.getRole()==2){
                activityService.deleteActivity(activity.getAid());
                return CommonResult.success("删除成功");
            }
            else{
                return CommonResult.error(401,"身份认证失败");
            }
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
            return CommonResult.error(400,"身份识别出现错误");
        }
    }
}
