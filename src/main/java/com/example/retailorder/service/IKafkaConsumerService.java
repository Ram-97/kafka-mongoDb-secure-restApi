package com.example.retailorder.service;

import com.example.retailorder.model.Order;

public interface IKafkaConsumerService {
	void consume(Order order);
}
