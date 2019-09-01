package com.personaldata.pdmdemo.good;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.personaldata.pdmdemo.mapper.AddNewItemsModel;
import com.personaldata.pdmdemo.mapper.TableMapper.ItemMapper;
import com.personaldata.pdmdemo.s3bucket.GeneratePresignedURL;
import com.personaldata.pdmdemo.s3bucket.UploadObject;
import com.personaldata.pdmdemo.s3bucket.UploadWebPage;


@Controller
public class AddNewItemsController {
	String tableName = "test";
	String bucketName = tableName+"-pdm-demo-s3bucket";
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    String create_time = df.format(new Date());
    

	@RequestMapping(value="/pdm/UpdateAddNewItems", method=RequestMethod.POST)
	//public String helloAddNewItems(@RequestBody String getJsonData , Model model) {
	public String UpdateAddNewItems(@RequestBody List<ItemMapper> getJsonData , Model model) {
		getJsonData.forEach(System.out::println);
		AddNewItemsModel.UpdateTodayData(tableName, getJsonData);
		
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		return "user/pages/AddNewItems";

		/*
		//Add list data to AddNewItems.html
		String tableName = "root";
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		return "user/pages/AddNewItems";
	*/
	}
	
	@RequestMapping(value="/pdm/DeleteAddNewItems", method=RequestMethod.POST)
	//public String helloAddNewItems(@RequestBody String getJsonData , Model model) {
	public String DeleteAddNewItems(@RequestBody List<ItemMapper> getJsonData , Model model) {
		//System.out.println("get delete json data ================>>");
		//System.out.println(getJsonData);
		//getJsonData.forEach(System.out::println);
		//System.out.println("Today: json data ================>>");
		AddNewItemsModel.DeleteTodayData(tableName, getJsonData);
		
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		System.out.println(resultList);
		return "user/pages/AddNewItems";
	}
	
	@RequestMapping(value="/pdm/gotoAddNewItems", method=RequestMethod.GET)
	public String AddNewItemGet(Model model) {
		//Add list data to AddNewItems.html
		//String tableName = "root";
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		//resultList.forEach(System.out::println); 
		return "user/pages/AddNewItems";
	}
	
	@RequestMapping(value="/pdm/AddNewItems", method=RequestMethod.POST)
	public String AddNewItemSubmit(@ModelAttribute("AddItemForm") AddItemForm addItemForm, Model model) throws IOException {
		//String tableName = "root";

		//String create_time;
		String category = addItemForm.getCategory();
        String people = addItemForm.getPeople();
        String event_location = addItemForm.getEvent_location();
        String historical_time = addItemForm.getHistorical_time();
        String description = addItemForm.getDescription();
        String web_link = addItemForm.getWeb_link();
        String importance = addItemForm.getImportance();
        String tag = addItemForm.getTag();
        String priority = addItemForm.getPriority();
		/*
		 * MultipartFile multipartFile = addItemForm.getMultipartFile();
		 * 
		 * String newfilePath = addItemForm.getNewfilePath();
		 */
        if(importance == "Yes") {
        	System.out.println("importance: "+importance);
        	System.out.println("upload web page!!!!!!!!!!!!!!!!!!!!");
        	String fileObjKeyName="testwebpage.html";
        	String bucketName=tableName+"-pdm-demo-s3bucket";
        	UploadWebPage.uploadWebPage(bucketName, fileObjKeyName, web_link);
        }else {
        	System.out.println("importance: "+importance);
        }
		try {
			String fileName = addItemForm.getOriginalFilename();

//			if (fileName.isEmpty() && fileName == "") {
			if (fileName.isEmpty()) {
				String fileObjKeyName = "";
				String fileLink = "";
				// AddNewItemsModel.TodayData(tableName);
				AddNewItemsModel.AddSingleItem(tableName, category, people, event_location, historical_time,
						description, web_link, importance, tag, priority, fileObjKeyName, fileLink);
			} else {				
				System.out.println(fileName);
				String fileObjKeyName = create_time + "_" + fileName;
				//String fileObjKeyName="hello";
				UploadObject.uploadObject(bucketName, fileObjKeyName, addItemForm.getMultipartFile());// upload object
				String fileLink = GeneratePresignedURL.generateURL(bucketName, fileObjKeyName); // get object link
				AddNewItemsModel.AddSingleItem(tableName, category, people, event_location, historical_time,
						description, web_link, importance, tag, priority, fileObjKeyName, fileLink);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.err.println("Check AddNewItemsController -> AddNewItemSubmit");
			System.err.println(e.getMessage());
			
		}
		
		//Add list data to AddNewItems.html
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		//System.out.println("model.addAttribute(\"resultList\", resultList);");
		return "redirect:/pdm/gotoAddNewItems";
		//return "user/pages/AddNewItems";
	}
	
/*	
	@RequestMapping(value="/table", method=RequestMethod.GET)
	public String testtable(Model model) {
		return "user/pages/tables";
	}
	*/
}
