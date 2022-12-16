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
	public void addData(@RequestBody DataModel2 dataModel) {
		iDataService.addDataModel(dataModel);
	}
	
	@GetMapping("/searchPan")
	public List<String> getPanCards(@RequestBody DataModel2 model) {
		String panCard = model.getPanCard();
		List<String> panCardList = iDataService.searchPanCard(panCard);
		return panCardList;
	}

}
