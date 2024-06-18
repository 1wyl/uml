package com.example.backend.relative;
import lombok.Data;

@Data
public class Relative {
    private int rid;
    private int id; // Foreign key
    private String relation;
    private String name;
    private int age;
    private String gender;
    private String job;
}
