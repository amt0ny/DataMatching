package com.match.test.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.match.test.entity.DataModel2;
import com.match.test.response.ResponseMessage;
import com.match.test.service.IDataService;

@RestController
public class DataController {

	@Autowired
	private IDataService iDataService;
	

	/**
	 * API to add Data into Database
	 */
	@PostMapping("/addData")
	public String addData(@RequestBody DataModel2 dataModel) {
		return iDataService.addDataModel(dataModel.getName());
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
	
	/**
	 * This API is used to search Users by two small part of 'Pan-card' and 'Name'
	 * Ex: Name: Pritam-> Pri,  Pan-card: PA1K241SK-> PA1K2
	 * 
	 * 	 
	 */
	@GetMapping("/getByNameAndPan")
	public List<String> getByNameAndPan(@RequestBody DataModel2 model) {
		List<String> resultList= iDataService.searchByNameAndPan(model);
		
		return resultList;
	}
	
	/**
	 * This API is to check whether 'User' already exist or not using Pan-card and Aadhar-Card
	 *  And also checking that Pan or Aadhar we got is null or not
	 */
	@GetMapping("/checkByPanAndAadharCard")
	public ResponseEntity<ResponseMessage> getByPanAndAadhar(@RequestBody DataModel2 dataModel) {

		ResponseMessage nullCheckResponseMessage2 = iDataService.nullCheckForPancardAndAadharCard(dataModel.getPanCard(),dataModel.getAadharCard(), "");
		DataModel2 newModel = iDataService.findByPancardAndAadharCard(dataModel.getPanCard(),dataModel.getAadharCard());

		if (newModel != null) {
			nullCheckResponseMessage2 = iDataService.nullCheckForPancardAndAadharCard(dataModel.getPanCard(), dataModel.getAadharCard(), "User exists already");
		} else {
			nullCheckResponseMessage2 = iDataService.nullCheckForPancardAndAadharCard(dataModel.getPanCard(),dataModel.getAadharCard(), "Record doesn't exist");
		}

		return new ResponseEntity<>(nullCheckResponseMessage2, HttpStatus.OK);
	}

}
