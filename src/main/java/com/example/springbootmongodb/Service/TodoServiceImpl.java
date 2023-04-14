package com.example.springbootmongodb.Service;

import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootmongodb.DTO.TodoDTO;
import com.example.springbootmongodb.Exception.TodoCollectionException;
import com.example.springbootmongodb.Repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodoDTO> todoOp = todoRepository.findByTodo(todo.getTodo());
        if (todoOp.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExistsException());
        } else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todo);
        }
    }

}
