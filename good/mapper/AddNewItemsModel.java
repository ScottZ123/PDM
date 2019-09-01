package com.personaldata.pdmdemo.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.personaldata.pdmdemo.mapper.TableMapper.ItemMapper;
import com.personaldata.pdmdemo.s3bucket.UploadWebPage;

public class AddNewItemsModel {
	//static AmazonDynamoDB client = ConnectToDB.dynamoDB();
	public static void AddSingleItem(String tableName, String category, String people, 
							  String event_location, String historical_time, String description, 
							  String web_link, String importance, String tag, String priority, String fileName, String fileLink) 
			throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//set date format
		//SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//set date format
        //System.out.println(df.format(new Date()));// new Date() get current system time
        String create_time = df.format(new Date());
		//System.out.println("AddNewItemsModel =>> Create time " + create_time + "category" + category);
        
		ItemMapper item = new ItemMapper();
		item.setCreate_time(create_time);
        item.setCategory(category);
        item.setPeople(people);
		item.setEvent_location(event_location);
		item.setHistorical_time(historical_time);
		item.setDescription(description);
		item.setWeb_link(web_link);
		item.setImportantce(importance);
		item.setTag(tag);
		item.setFileName(fileName);
		item.setFileLink(fileLink);		

		//item.setPriority(priority);
		DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
		
    	// Save the item
        mapper.save(item);
/*        
        // Retrieve the item.
        ItemMapper itemRetrieved = mapper.load(ItemMapper.class, create_time,category);
        System.out.println("AddNewItemsModel=>AddSingleItem=>Item retrieved:");
        System.out.println(itemRetrieved);       
        }
        */
	}   
	public static List<ItemMapper> TodayData(String tableName) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//set date format     
		String create_time_beginWith = df.format(new Date());
    
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        //eav.put(":v1", new AttributeValue().withS(create_time_beginWith));
        eav.put(":v1", new AttributeValue().withS(create_time_beginWith));
        //eav.put(":v2",new AttributeValue().withS(create_time_end_time));
               
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression("begins_with(create_time,:v1)")
            .withExpressionAttributeValues(eav);

        DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
        List<ItemMapper> DataCreatedToday =  mapper.scan(ItemMapper.class, scanExpression);
        //System.out.println("===============show today=========================");
        //DataCreatedToday.forEach(System.out::println);	
        return DataCreatedToday;
	}
	
	public static void UpdateTodayData(String tableName, List<ItemMapper> getJsonData) {
        DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
        mapper.batchSave(getJsonData);
	}
	
	public static void DeleteTodayData(String tableName, List<ItemMapper> getJsonData) {
        DynamoDBMapper mapper = ConnectToDB.mapperConfig(tableName);
        mapper.batchDelete(getJsonData);
        
	}
}



