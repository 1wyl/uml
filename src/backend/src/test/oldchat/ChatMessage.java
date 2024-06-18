package com.example.backend.oldchat;

import lombok.Data;

import java.util.Map;

@Data
public class ChatMessage {
    private String content;
    private String sessionid;
    private Map<String, String> queryParams;
}
