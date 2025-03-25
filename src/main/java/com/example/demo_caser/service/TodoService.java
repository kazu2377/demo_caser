package com.example.demo_caser.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo_caser.model.Todo;
import com.example.demo_caser.model.User;
import com.example.demo_caser.repository.TodoRepository;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    public List<Todo> getAllTodos() {
        User currentUser = getCurrentUser();
        return todoRepository.findByUserId(currentUser.getId());
    }
    
    public Todo getTodoById(Long id) {
        User currentUser = getCurrentUser();
        return todoRepository.findByIdAndUserId(id, currentUser.getId());
    }
    
    public Todo createTodo(String title) {
        User currentUser = getCurrentUser();
        Todo todo = new Todo(title, currentUser.getId());
        todoRepository.save(todo);
        return todo;
    }
    
    public Todo updateTodo(Long id, String title, Boolean completed) {
        User currentUser = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUserId(id, currentUser.getId());
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
        User currentUser = getCurrentUser();
        todoRepository.deleteByIdAndUserId(id, currentUser.getId());
    }
    
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        // デバッグ情報を追加
        if (authentication != null) {
            System.out.println("Principal class: " + authentication.getPrincipal().getClass().getName());
            System.out.println("Principal: " + authentication.getPrincipal().toString());
            System.out.println("Authorities: " + authentication.getAuthorities());
        }
        throw new IllegalStateException("認証されたユーザーが見つかりません。ログインしてください。");
    }
}