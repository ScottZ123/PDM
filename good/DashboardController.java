package com.personaldata.pdmdemo.good;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

	@RequestMapping(value="/pdm/gotoIndex", method=RequestMethod.GET)
	public String index(Model model) {
		return "/admin/pages/index";
	}	
}
