package com.example.retailorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDBConfig {

	    @Bean
	    public MongoDatabaseFactory mongoDbFactory() {
	        MongoClient mongoClient = MongoClients.create();
	        return new SimpleMongoClientDatabaseFactory(mongoClient, "retailorder" );
	    }

	    @Bean
	    public MongoTemplate mongoTemplate() {
	        return new MongoTemplate(mongoDbFactory());
	    }
}
