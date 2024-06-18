package com.example.backend;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Event {
    private String name;
    private LocalDateTime time;
    private String address;
    private String location;
    private String description;
    private Boolean isSolved;
    private String phone;
}
