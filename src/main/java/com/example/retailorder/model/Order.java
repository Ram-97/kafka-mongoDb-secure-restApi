package com.example.retailorder.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document()
public class Order {
	@MongoId(FieldType.OBJECT_ID)
	public String id;
	public String status;
	public Date orderDate;
	public Date shippedDate;
	public Date requiredDate;
	
	@Override
	public String toString() {
		return "Order [status=" + status + ", orderDate=" + orderDate + ", shippedDate=" + shippedDate
				+ ", requiredDate=" + requiredDate + "]";
	}
}
