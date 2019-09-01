package com.personaldata.pdmdemo.good;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.personaldata.pdmdemo.mapper.AddNewItemsModel;
import com.personaldata.pdmdemo.mapper.GetAllItemsModel;
import com.personaldata.pdmdemo.mapper.TableMapper.ItemMapper;

@Controller
public class GetAllItemsController {
	String tableName = "test";
	
	@RequestMapping(value="/pdm/gotoGetAllItems", method=RequestMethod.GET)
	public String AllItemsGet(Model model) {
		List<ItemMapper> resultList = GetAllItemsModel.GetAllItems(tableName);
		model.addAttribute("resultList", resultList);
		return "user/pages/GetAllItems";
	}
	
	@RequestMapping(value="/pdm/UpdateGetAllItems", method=RequestMethod.POST)
	public String UpdateGetAllItems(@RequestBody List<ItemMapper> getJsonData , Model model) {
		getJsonData.forEach(System.out::println);
		GetAllItemsModel.UpdateGetAllData(tableName, getJsonData);
		
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		//getJsonData.forEach(System.out::println);
		return "user/pages/GetAllItems";
	}
	
	@RequestMapping(value="/pdm/DeleteGetAllItems", method=RequestMethod.POST)
	//public String helloAddNewItems(@RequestBody String getJsonData , Model model) {
	public String DeleteAddNewItems(@RequestBody List<ItemMapper> getJsonData , Model model) {

		//getJsonData.forEach(System.out::println);

		GetAllItemsModel.DeleteGetAllData(tableName, getJsonData);
		
		List<ItemMapper> resultList = AddNewItemsModel.TodayData(tableName);
		model.addAttribute("resultList", resultList);
		
		return "user/pages/GetAllItems";
	}
}
