package com.example.retailorder.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.retailorder.config.AppConstants;
import com.example.retailorder.model.Order;
 
@Service
public class KafKaConsumerService implements IKafkaConsumerService
{
	@Autowired
	private IRetailOrderService retailOrderService;
	
    private final Logger logger 
        = LoggerFactory.getLogger(KafKaConsumerService.class);
     
	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
	public void consume(Order order) {
		logger.info(String.format("Order consumed -> %s", order.toString()));
		order.status = "PROCESSED";
		order.shippedDate = new Date();
		retailOrderService.updateOrder(order);
	}

}
