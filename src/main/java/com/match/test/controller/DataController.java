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

	/**
	 * API to add Data into Database
	 */
	@PostMapping("/addData")
	public void addData() {
		iDataService.addDataModel();
	}

	/**
	 * API to get a list of 'Pancards' from Database similar to our 'Input'
	 */
	@GetMapping("/searchPanCard")
	public List<String> getPanCards(@RequestBody DataModel2 model) {
		String pan = model.getPanCard();
		List<String> panCardList = iDataService.searchPanCard(pan);
		return panCardList;
	}

	/**
	 * This method is to get a list of Similar "Names" avilable in our database with
	 * matching percentage of 70
	 */
	@GetMapping("/searchByName")
	public List<String> getName(@RequestBody DataModel2 model) {
		String name = model.getName();
		List<String> userNameList = iDataService.searchName(name);
		return userNameList;
	}
	
	@GetMapping("/getByNamePan")
	public List<String> getByNameAndPan(@RequestBody DataModel2 model) {
		List<String> resultList= iDataService.searchByNameAndPan(model);
		
		return resultList;
		
	}

}
