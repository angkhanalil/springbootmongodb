package com.example.springbootmongodb.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootmongodb.DTO.TodoDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

    @Query("{'todo':?0}")
    Optional<TodoDTO> findByTodo(String todo);
}
