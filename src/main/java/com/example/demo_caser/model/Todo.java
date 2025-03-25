package com.example.demo_caser.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private Long userId;
    
    public Todo(String title, Long userId) {
        this.title = title;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.userId = userId;
    }
}