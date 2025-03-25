package com.example.demo_caser.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo_caser.model.Todo;
import com.example.demo_caser.service.TodoService;

@Controller
public class TodoController {
    
    private final TodoService todoService;
    
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping("/todos")
    public String listTodos(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }
    
    @PostMapping("/todos")
    public String createTodo(@RequestParam("title") String title) {
        todoService.createTodo(title);
        return "redirect:/todos";
    }
    
    @PostMapping("/todos/{id}/complete")
    public String completeTodo(@PathVariable("id") Long id) {
        todoService.updateTodo(id, null, true);
        return "redirect:/todos";
    }
    
    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
} 