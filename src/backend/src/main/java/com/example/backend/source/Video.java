package com.example.backend.source;

import lombok.Data;

import java.util.Date;

@Data
public class Video{
    int uid;
    String description;
    Date date;
    String url;
}