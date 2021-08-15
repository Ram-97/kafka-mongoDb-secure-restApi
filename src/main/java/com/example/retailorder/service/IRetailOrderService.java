package com.example.retailorder.service;

import java.util.List;

import com.example.retailorder.model.Order;

public interface IRetailOrderService {

	public List<Order> getAllOrders();
	public List<Order> getOrdersByStatus(String status);
	public Order getOrderById(String id);
	public Order createOrder(Order newOrder);
	public void updateOrder(Order order);
}
