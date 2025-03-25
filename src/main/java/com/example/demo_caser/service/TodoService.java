package com.example.demo_caser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_caser.model.Todo;
import com.example.demo_caser.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    public Todo createTodo(String title) {
        Todo todo = new Todo(title);
        return todoRepository.save(todo);
    }
    
    public Todo updateTodo(Long id, String title, Boolean completed) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            if (title != null) {
                todo.setTitle(title);
            }
            if (completed != null) {
                todo.setCompleted(completed);
            }
            return todoRepository.save(todo);
        }
        return null;
    }
    
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
} 