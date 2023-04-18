package com.example.springbootmongodb.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmongodb.DTO.TodoDTO;
import com.example.springbootmongodb.Exception.TodoCollectionException;
import com.example.springbootmongodb.Repository.TodoRepository;
import com.example.springbootmongodb.Service.TodoService;

@RestController
public class TodoController {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private TodoService todoService;

  @GetMapping("/todos")
  public ResponseEntity<?> getAllTodos() {
    List<TodoDTO> todos = todoService.getAllTodos();
    // if (todos.size() > 0) {
    // return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
    // }
    return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  @PostMapping("/todos")
  public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
    try {
      // todo.setCreatedAt(new Date(System.currentTimeMillis()));

      // todoRepository.save(todo);
      todoService.createTodo(todo);
      return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
    } catch (ConstraintViolationException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

    } catch (TodoCollectionException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  @GetMapping("/todos/{id}")
  public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
    // Optional<TodoDTO> todoOptional = todoRepository.findById(id);
    // if (todoOptional.isPresent()) {
    // return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>("Todo not found with id:" + id,
    // HttpStatus.NOT_FOUND);
    // }

    // use service
    try {
      return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/todos/{id}")
  public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo) {
    // Optional<TodoDTO> todoOptional = todoRepository.findById(id);
    // if (todoOptional.isPresent()) {
    // TodoDTO todoToSave = todoOptional.get();
    // todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() :
    // todoToSave.getCompleted());
    // todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() :
    // todoToSave.getTodo());
    // todoToSave.setDescription(todo.getDescription() != null ?
    // todo.getDescription() : todoToSave.getDescription());
    // todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
    // todoRepository.save(todoToSave);
    // return new ResponseEntity<>(todoToSave, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>("Todo not found with id:" + id,
    // HttpStatus.NOT_FOUND);
    // }

    /// Service
    try {
      todoService.updateTodo(id, todo);
      return new ResponseEntity<>("Updated successfully with Id:" + id, HttpStatus.OK);

    } catch (ConstraintViolationException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (TodoCollectionException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/todos/{id}")
  public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
    try {
      todoRepository.deleteById(id);
      return new ResponseEntity<>("Success deleted with id:" + id, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
  }

}
