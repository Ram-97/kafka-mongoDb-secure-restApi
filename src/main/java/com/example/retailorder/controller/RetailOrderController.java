package com.example.retailorder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retailorder.model.Order;
import com.example.retailorder.service.IRetailOrderService;

@RestController
@RequestMapping("api/retail-order")
public class RetailOrderController {

	@Autowired
	private IRetailOrderService retailOrderService;
    private static final Logger logger = 
            LoggerFactory.getLogger(RetailOrderController.class);
    
	@GetMapping()
	public ResponseEntity<Object> getAllOrders(){
		try {
			logger.info("Controller: getAllOrders action invoked");
			return ResponseEntity.ok(retailOrderService.getAllOrders());
		} catch (Exception e) {
			logger.error("Error: getAllOrders controller : " + e.toString());
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("filter")
	public ResponseEntity<Object> getOrdersByStatus(String status){
		try {
			logger.info("Controller: getOrdersByStatus action invoked");
			return ResponseEntity.ok(retailOrderService.getOrdersByStatus(status));
		} catch (Exception e) {
			logger.error("Error: getOrdersByStatus controller : " + e.toString());
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable String id){
		try {
			logger.info("Controller: getOrderById action invoked");
			return ResponseEntity.ok(retailOrderService.getOrderById(id));
		} catch (Exception e) {
			logger.error("Error: getOrderById controller : Input "+ id +":" + e.toString());
			return new ResponseEntity<>("Order is not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("add")
	public ResponseEntity<Object> createOrder(@RequestBody Order newOrder){
		try {
			logger.info("Controller: createOrder action invoked");
			return new ResponseEntity<>(retailOrderService.createOrder(newOrder),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error: createOrder controller : Input " + newOrder.toString() +":"+ e.toString());
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
}
