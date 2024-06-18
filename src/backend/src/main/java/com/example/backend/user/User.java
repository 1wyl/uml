package com.example.backend.user;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private int uid;
    private String username;
    private String password;
    private int role;
    private String name;
    private String gender;
    private Date birthDate;
    private int age;
    private String address;
    private String phone;
    private String description;
    private double baseSalary;
    private double extraSalary;
    private double totalSalary;
}
