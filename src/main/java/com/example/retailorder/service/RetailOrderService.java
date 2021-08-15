package com.example.retailorder.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.example.retailorder.model.Order;
import com.example.retailorder.repository.RetailOrderRepository;

@Service
public class RetailOrderService implements IRetailOrderService {

	@Autowired
	private RetailOrderRepository retailOrderRepo;
	@Autowired
	private IProducerService producerService;
	
    private static final Logger logger = 
            LoggerFactory.getLogger(RetailOrderService.class);

	public List<Order> getAllOrders() {
		try {
			logger.info("Service: getAllOrders method invoked");
			return retailOrderRepo.findAll();
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public List<Order> getOrdersByStatus(String status) {
		try {
			logger.info("Service: getOrdersByStatus method invoked");
			return retailOrderRepo.findByStatus(status);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public Order getOrderById(String id) {
		try {
			logger.info("Service: getOrderById method invoked");
			Optional<Order> foundOrder = retailOrderRepo.findById(id);
			if(foundOrder.isEmpty()){
				return null;
			}
			return foundOrder.get();
		} catch (Exception ex) {
			throw ex;
		}
	}

	public Order createOrder(Order newOrder) {
		try {
			logger.info("Service: createOrder method invoked");
			newOrder.status = "PLACED";
			newOrder = retailOrderRepo.save(newOrder);
			producerService.pushOrder(newOrder);
			return newOrder;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void updateOrder(Order order) {
		try {
			retailOrderRepo.save(order);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
