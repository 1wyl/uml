package com.example.backend.activities;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityMapper {
    @Select("SELECT * FROM Activities")
    List<Activity> getAllActivities();

    @Delete("DELETE FROM Activities WHERE aid = #{aid}")
    void deleteActivity(@Param("aid") Long aid);
    @Insert("INSERT INTO activities(name,location,uploadTime, StartTime) VALUES (#{activity.name}, #{activity.location},#{activity.uploadTime}, #{activity.startTime})")
    void addActivity(@Param("activity") Activity activity);
}
