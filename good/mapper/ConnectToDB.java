package com.personaldata.pdmdemo.mapper;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class ConnectToDB {
	public static AmazonDynamoDB dynamoDB(){
  		   	
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        		.withEndpointConfiguration(new AwsClientBuilder
            		.EndpointConfiguration("http://localhost:8000", "eu-west-1"))
            .build();
/* 	
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    			.withRegion(Regions.EU_WEST_1 )
    			.build();
  */ 	
    	 return client;
	}
	
	public static DynamoDBMapper mapperConfig(String tableName) {
		AmazonDynamoDB client = ConnectToDB.dynamoDB();
		
		// DynamoDBMapper Configuration - dynamic change table name
    	TableNameOverride tbl = new TableNameOverride(tableName);
    	DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
    	        .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
    	        .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
    	        .withTableNameOverride(tbl)
    	        .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING)
    	    .build();
    	
    	DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);
    	
    	return mapper;
	}
}