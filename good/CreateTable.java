package com.personaldata.pdmdemo.good;

import java.util.Arrays;

import com.amazonaws.regions.Regions;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.personaldata.pdmdemo.mapper.ConnectToDB;

public class CreateTable{ 
	public static void createTb(String tableName) throws Exception {
		
		DynamoDB dynamoDB = new DynamoDB(ConnectToDB.dynamoDB());
	
			try {
				System.out.println("Attempting to create table; please wait...");
				
				Table table = dynamoDB.createTable(tableName, // specify table name, primary key attributes, and its data types
						Arrays.asList(new KeySchemaElement("item_uuid", KeyType.HASH)), // Partition Key
								//new KeySchemaElement("category", KeyType.RANGE)), // Sort key
	
						// The ScalarAttributeType is N for number.
						// The ScalarAttributeType is S for string.
	
						Arrays.asList(new AttributeDefinition("item_uuid", ScalarAttributeType.S)),
								//new AttributeDefinition("category", ScalarAttributeType.S)),
						new ProvisionedThroughput(10L, 10L)); // The ProvisionedThroughput parameter is required
				table.waitForActive();
				System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
	
			} catch (Exception e) {
				System.err.println("Unable to create table: ");
				System.err.println(e.getMessage());
			}
		}
}

