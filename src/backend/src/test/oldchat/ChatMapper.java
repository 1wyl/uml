package com.example.backend.oldchat;

import org.apache.ibatis.annotations.Mapper;

//@Repository
@Mapper
public interface ChatMapper {
    void save(ChatMessage message);
}
