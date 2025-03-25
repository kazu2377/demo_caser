package com.example.demo_caser.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo_caser.model.Todo;

@Mapper
public interface TodoRepository {
    
    @Select("SELECT * FROM todos")
    List<Todo> findAll();
    
    @Select("SELECT * FROM todos WHERE user_id = #{userId}")
    List<Todo> findByUserId(@Param("userId") Long userId);
    
    @Select("SELECT * FROM todos WHERE id = #{id}")
    Todo findById(@Param("id") Long id);
    
    @Select("SELECT * FROM todos WHERE id = #{id} AND user_id = #{userId}")
    Todo findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
    
    @Insert("INSERT INTO todos(title, completed, created_at, user_id) VALUES(#{title}, #{completed}, #{createdAt}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Todo todo);
    
    @Update("UPDATE todos SET title = #{title}, completed = #{completed} WHERE id = #{id}")
    int update(Todo todo);
    
    @Delete("DELETE FROM todos WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    @Delete("DELETE FROM todos WHERE id = #{id} AND user_id = #{userId}")
    int deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
}