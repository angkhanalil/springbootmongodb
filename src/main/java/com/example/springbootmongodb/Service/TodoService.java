package com.example.springbootmongodb.Service;

import javax.validation.ConstraintViolationException;

import com.example.springbootmongodb.DTO.TodoDTO;
import com.example.springbootmongodb.Exception.TodoCollectionException;

public interface TodoService {

    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;
}