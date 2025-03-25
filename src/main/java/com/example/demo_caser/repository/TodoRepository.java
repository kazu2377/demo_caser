package com.example.demo_caser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_caser.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
} 