package com.example.demo_caser.service;

import java.util.List;

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
        return todoRepository.findById(id);
    }
    
    public Todo createTodo(String title) {
        Todo todo = new Todo(title);
        todoRepository.save(todo);
        return todo;
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
            todoRepository.update(todo);
            return todo;
        }
        return null;
    }
    
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}