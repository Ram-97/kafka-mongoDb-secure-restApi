package com.example.retailorder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.retailorder.model.AuthenticationRequest;

public interface UserRepository extends MongoRepository<AuthenticationRequest, String>{
	AuthenticationRequest findByUsername(String username);
}
