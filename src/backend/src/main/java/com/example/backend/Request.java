package com.example.backend;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Request {
    private int rid;
    private String name;
    private String address;
    private LocalDateTime requestTime;
    private String phone;
    private String serviceType;
    private String productName;
    private boolean completionStatus;
}
