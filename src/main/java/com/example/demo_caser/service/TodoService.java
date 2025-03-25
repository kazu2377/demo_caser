package com.example.demo_caser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo_caser.model.Todo;
import com.example.demo_caser.repository.TodoRepository;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    public Todo getTodoById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        return todoOptional.orElse(null);
    }
    
    public Todo createTodo(String title) {
        Todo todo = new Todo(title);
        return todoRepository.save(todo);
    }
    
    public Todo updateTodo(Long id, String title, Boolean completed) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
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