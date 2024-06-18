package com.example.backend.activities;

import com.example.backend.activities.Activity;
import com.example.backend.activities.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    // 获取所有活动
    public List<Activity> getAllActivities() {
        return activityMapper.getAllActivities();
    }

    public void addActivity(Activity activity){
        activityMapper.addActivity(activity);
    }
    // 删除活动
    public void deleteActivity(Long aid) {
        activityMapper.deleteActivity(aid);
    }
}
