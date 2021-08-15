package com.example.retailorder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.retailorder.config.AppConstants;
import com.example.retailorder.model.Order;
 
@Service
public class KafKaProducerService implements IProducerService
{
    private static final Logger logger = 
            LoggerFactory.getLogger(KafKaProducerService.class);
        
    @Autowired
    private KafkaTemplate<String, Order> retailOrderKafkaTemplate;
         
    public void pushOrder(Order newOrder) 
    {
		logger.info(String.format("Order created -> %s", newOrder.toString()));
		this.retailOrderKafkaTemplate.send(AppConstants.TOPIC_NAME, newOrder);
    }
}