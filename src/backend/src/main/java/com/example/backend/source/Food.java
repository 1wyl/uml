package com.example.backend.source;

import lombok.Data;

import java.util.Date;

@Data
public class Food {
    private int fid;
    private String description;
    private Date date;
    private String url;
}

