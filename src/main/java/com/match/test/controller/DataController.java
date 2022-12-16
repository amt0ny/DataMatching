package com.match.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.match.test.entity.DataModel2;
import com.match.test.service.IDataService;


import me.xdrop.fuzzywuzzy.FuzzySearch;

@RestController
public class DataController {
	
	@Autowired
	private IDataService iDataService;
	
	@PostMapping("/addData")
	public void addData(@RequestBody DataModel2 dataModel) {
		iDataService.addDataModel(dataModel);
	}
	
	@GetMapping("/searchPan")
	public String getPanCards(@RequestBody DataModel2 model) {
		String pan = model.getPanCard();
		String panCard = iDataService.searchPanCard(pan);
		return panCard;
	}
	
	@GetMapping("/getByName")
	public List<String> getByNameHandler(@RequestBody DataModel2 dataModel2) {
         
		List<String> list = new ArrayList<>();
		
		iDataService.findAll().forEach((e)->{
			int ratio = FuzzySearch.ratio(dataModel2.getName(),e.getName());
			if(ratio>70) {
				list.add(e.getName());
			}
		});
	
		return list;
	}

}
