package com.example.backend.source;

import lombok.Data;

import java.util.Date;

@Data
public class Medicine {
    private int mid;
    private String description;
    private Date date;
    private String url;
}
