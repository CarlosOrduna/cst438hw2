package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cst438hw2.domain.*;
import cst438hw2.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {

		// TODO Your code goes here
		// TODO delete the following line
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		model.addAttribute("city", cityInfo);
		return "city_info";
	} 
	
}