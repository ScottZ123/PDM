package com.personaldata.pdmdemo.mapper;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import com.personaldata.pdmdemo.mapper.TableMapper.ItemMapper;

public class GetAllItemsModel {

	public static List<ItemMapper> GetAllItems(String tableName) {
               
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
        List<ItemMapper> GetAllItems =  mapper.scan(ItemMapper.class, scanExpression);
        //GetAllItems.forEach(System.out::println);	
        return GetAllItems;
	}
	
	public static void UpdateGetAllData(String tableName, List<ItemMapper> getJsonData) {
        DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
        mapper.batchSave(getJsonData);
	}
	
	public static void DeleteGetAllData(String tableName, List<ItemMapper> getJsonData) {
        DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
        mapper.batchDelete(getJsonData);
	}
}
