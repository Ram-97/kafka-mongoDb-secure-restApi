package com.example.retailorder.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.retailorder.model.Order;

public interface RetailOrderRepository extends MongoRepository<Order, String>{
	List<Order> findByStatus(String status);
}
