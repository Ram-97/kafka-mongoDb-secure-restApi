package com.example.retailorder.service;

import com.example.retailorder.model.Order;

public interface IProducerService {
	void pushOrder(Order newOrder);
}
