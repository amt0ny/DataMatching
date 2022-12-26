package com.match.test.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.match.test.entity.DataModel2;
import com.match.test.response.ResponseMessage;
import com.match.test.service.IDataService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DataController {

	@Autowired
	Gson gson;

	@Autowired
	private IDataService iDataService;

	/**
	 * API to add Data into Database
	 */
	@PostMapping(value = "/addData", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addData(@RequestBody String dataModel) {
		DataModel2 dataModel3 = gson.fromJson(dataModel, DataModel2.class);
		return iDataService.addDataModel(dataModel3.getName());
	}

	/**
	 * API to get a list of 'Pancards' from Database similar to our 'Input'
	 */
	@GetMapping(path = "/searchPanCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getPanCards(@RequestBody DataModel2 model) {
		log.info("searchPanCard method start");
		String pan = model.getPanCard();
		List<String> panCardList = iDataService.searchPanCard(pan);
		log.info("searchPanCard method end");
		return panCardList;
	}

	/**
	 * This method is to get a list of Similar "Names" avilable in our database with
	 * matching percentage of 70
	 */
	@GetMapping(path = "/searchByName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getName(@RequestBody String model) {
		log.info("searchByName method start");
		DataModel2 dataModel = gson.fromJson(model, DataModel2.class);
		String name = dataModel.getName();
		List<String> userNameList = iDataService.searchName(name);
		log.info("searchByName method end");
		return userNameList;
	}

	/**
	 * This API is used to search Users by two small part of 'Pan-card' and 'Name'
	 * Ex: Name: Pritam-> Pri, Pan-card: PA1K241SK-> PA1K2
	 */
	@GetMapping(path = "/getByNameAndPan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getByNameAndPan(@RequestBody String model) {
		log.info("getByNameAndPan method start");
		DataModel2 dataModel3 = gson.fromJson(model, DataModel2.class);
		System.out.println("DataModel  = "+dataModel3);
		List<String> resultList = iDataService.searchByNameAndPan(dataModel3);
		log.info("getByNameAndPan method end");
		return resultList;
	}

	/**
	 * This API is to check whether 'User' already exist or not using Pan-card and
	 * Aadhar-Card And also checking that Pan or Aadhar we got is null or not
	 */
	@GetMapping(path = "/checkByPanAndAadharCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> getByPanAndAadhar(@RequestBody DataModel2 dataModel) {

		ResponseMessage nullCheckResponseMessage2 = iDataService
				.nullCheckForPancardAndAadharCard(dataModel.getPanCard(), dataModel.getAadharCard(), "");
		DataModel2 newModel = iDataService.findByPancardAndAadharCard(dataModel.getPanCard(),
				dataModel.getAadharCard());

		if (newModel != null) {
			nullCheckResponseMessage2 = iDataService.nullCheckForPancardAndAadharCard(dataModel.getPanCard(),
					dataModel.getAadharCard(), "User exists already");
		} else {
			nullCheckResponseMessage2 = iDataService.nullCheckForPancardAndAadharCard(dataModel.getPanCard(),
					dataModel.getAadharCard(), "Record doesn't exist");
		}

		return new ResponseEntity<>(nullCheckResponseMessage2, HttpStatus.OK);
	}

}
