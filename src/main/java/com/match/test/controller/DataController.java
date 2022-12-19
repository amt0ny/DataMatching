package com.match.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.match.test.entity.DataModel2;
import com.match.test.service.IDataService;


@RestController
public class DataController {
	
	@Autowired
	private IDataService iDataService;
	
	@PostMapping("/addData")
	public void addData() {
		iDataService.addDataModel();
	}
	
	@GetMapping("/searchPanCard")
	public String getPanCards(@RequestBody DataModel2 model) {
		String pan = model.getPanCard();
		String panCard = iDataService.searchPanCard(pan);
		return panCard;
	}
	
	@GetMapping("/searchByName")
	public List<String> getName(@RequestBody DataModel2 model) {
		String name = model.getName();
		List<String> userNameList = iDataService.searchName(name);
		return userNameList;
	}

}
