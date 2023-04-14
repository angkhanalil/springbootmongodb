package com.example.springbootmongodb.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootmongodb.DTO.TodoDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO,String>{
    
}
