package com.personaldata.pdmdemo.good;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
public class LoginController {
/*
	@RequestMapping(value = "/pdm/cookie", method = RequestMethod.GET)
	@ResponseBody
	public String createCookie(HttpServletResponse response, Model model) {
		Cookie newCookie = new Cookie("","");
		return null;
	}
	
	@RequestMapping(value = "/pdm/login", method = RequestMethod.GET)
    public String getLoginForm(Model model) {
		
        return "index";
    }

    @RequestMapping(value="/pdm/Dashboard", method=RequestMethod.POST)
    public String customerSubmit(@ModelAttribute("loginForm") LoginForm loginform, Model model) {

		if (loginform.getUsername().equals("test") && loginform.getPassword().equals("test")) {
			try {
				CreateTable.createTb(loginform.getUsername());
				String bucketName = loginform.getUsername() + "-pdm-demo-s3bucket";
				CreateBucket.createBucket(bucketName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Check NewLoginController -> customerSubmit");
				System.err.println(e.getMessage());
			}
			return "/admin/pages/index";
		}
		model.addAttribute("invalidCredentials", true);
		
		return "index";
	}
	*/
}


