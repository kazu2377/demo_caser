package com.example.demo_caser.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo_caser.model.Todo;
import com.example.demo_caser.repository.TodoRepository;
import com.example.demo_caser.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    
    public AdminController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/todos")
    public String adminTodos(Model model) {
        List<Todo> allTodos = todoRepository.findAll();
        model.addAttribute("todos", allTodos);
        return "admin/todos";
    }
    
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("todoCount", todoRepository.findAll().size());
        model.addAttribute("userCount", userRepository.findAll().size());
        return "admin/dashboard";
    }
} 