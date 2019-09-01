package com.personaldata.pdmdemo.good;

import java.util.Arrays;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;


@Controller
public class DeleteTbController {

    @RequestMapping(value="/pdm/DeleteTb", method=RequestMethod.POST)
    
	public String DeleteTbSubmit(@ModelAttribute("loginForm") LoginForm loginform, Model model) {
    	
    	model.addAttribute("tablename",loginform.getUsername());
		try {
			DeleteTable.deleteTb("test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Check DeleteTbController -> DeleteTbSubmit");
			System.err.println(e.getMessage());
			System.out.println(loginform.getUsername());
		}

		return "deleteTb";
	}
    
    @RequestMapping(value="/pdm/DeleteTbHome", method=RequestMethod.POST)  
//    @PostMapping("/deleteTb")
    //public String customerSubmit(@ModelAttribute AccountInfo user, Model model) {
	public String DeletePageReturn( Model model) {
			System.out.println("Back to home page!");

		return "home";
	}
}


