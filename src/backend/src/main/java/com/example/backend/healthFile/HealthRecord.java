package com.example.backend.healthFile;

import lombok.Data;

@Data
public class HealthRecord {
    private int uid;
    private String name;
    private String gender;
    private int age;
    private float height;
    private float weight;
    private String bloodType; // 血型
    private String allergies; // 过敏史
    private String medicalHistory; // 病史
    private String medications; // 当前用药
    private String smokingStatus; // 吸烟状态
    private String alcoholUse; // 饮酒情况
    private String physicalActivity; // 体力活动情况
    private String diet; // 饮食情况
    private String bloodPressure; // 血压情况
    private float cholesterolLevel; // 胆固醇水平
    private float glucoseLevel; // 血糖水平
}
