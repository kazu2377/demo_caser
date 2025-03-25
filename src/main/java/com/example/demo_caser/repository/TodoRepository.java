package com.example.demo_caser.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.demo_caser.model.Todo;

@Repository
public class TodoRepository {
    private final Map<Long, Todo> todos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();
    
    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }
    
    public Todo findById(Long id) {
        return todos.get(id);
    }
    
    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            todo.setId(counter.incrementAndGet());
        }
        todos.put(todo.getId(), todo);
        return todo;
    }
    
    public void deleteById(Long id) {
        todos.remove(id);
    }
} 