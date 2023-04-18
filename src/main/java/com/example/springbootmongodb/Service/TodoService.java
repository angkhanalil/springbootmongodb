package com.example.springbootmongodb.Service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.example.springbootmongodb.DTO.TodoDTO;
import com.example.springbootmongodb.Exception.TodoCollectionException;

public interface TodoService {

    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;

    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;

    public void deleteTodoById(String id) throws TodoCollectionException;
}
