package com.personaldata.pdmdemo.good;

import com.amazonaws.regions.Regions;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.personaldata.pdmdemo.mapper.ConnectToDB;

public class DeleteTable {

    public static void deleteTb(String tableName) throws Exception {
		
    	DynamoDB dynamoDB = new DynamoDB(ConnectToDB.dynamoDB());

        Table table = dynamoDB.getTable(tableName);

        try {
            System.out.println("Attempting to delete table; please wait...");
            table.delete();
            table.waitForDelete();
            System.out.print("Success.");

        }
        catch (Exception e) {
            System.err.println("Unable to delete table: ");
            System.err.println(e.getMessage());
        }
    }
}
