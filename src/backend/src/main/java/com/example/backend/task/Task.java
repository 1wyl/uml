package com.example.backend.task;


import lombok.Data;

@Data
public class Task {

    private Long id;

    private String name;

    private String ddl;

    private Integer uid;

    private boolean completed;

}
