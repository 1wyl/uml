package com.example.backend.relative;

import com.example.backend.task.Task;
import com.example.backend.user.User;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/relative")
public class RelativeController {
    @Autowired
    private UserService userService;

    @Autowired
    private RelativeService relativeService;

    @PostMapping("/add")
    public ResponseEntity<?> addRelative(@RequestBody Relative relative, @RequestHeader("Authorization") String ACCESS_TOKEN) {
//        relativeService.addRelative(relative);
        try {
            // 可能需要从 token 解析用户 ID
            System.out.println("1");
            String token = ACCESS_TOKEN.substring(7);
            User user = userService.getUserInfoByToken(token);
            relative.setId(user.getUid());  // 设置任务的用户 ID
            System.out.println("2");
            relativeService.addRelative(relative);
            return ResponseEntity.ok("信息添加成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("信息添加失败: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public List<Relative> getAllRelatives( @RequestHeader("Authorization") String ACCESS_TOKEN) throws Exception {
        String token = ACCESS_TOKEN.substring(7);
        User user = userService.getUserInfoByToken(token);
        int id = user.getUid();
        return relativeService.getAllRelatives(id);
    }

    @GetMapping("/{rid}")
    public ResponseEntity<?> getRelativeByRid(@PathVariable int rid, @RequestHeader("Authorization") String ACCESS_TOKEN) {
        try {
            String token = ACCESS_TOKEN.substring(7);
            User user = userService.getUserInfoByToken(token);
            Relative relative  = relativeService.findRelativeByRId(rid);
            if (relative != null && relative.getId()==user.getUid()) {
                return ResponseEntity.ok(relative);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("访问被拒绝");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRelative(@RequestHeader("Authorization") String ACCESS_TOKEN, @RequestBody Relative relative) {
        try {
                String token = ACCESS_TOKEN.substring(7);
                System.out.println(token);
                System.out.println(relative.getId());
                User user = userService.getUserInfoByToken(token);
                relative.setId(user.getUid());  // 设置任务的用户 ID
                relativeService.updateRelative(relative);
                return ResponseEntity.ok("任务更新成功");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("任务添加失败: " + e.getMessage());
        }
    }
}

