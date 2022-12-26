package com.match.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.match.test.entity.DataModel2;
import com.match.test.repo.IDataModelRepository;
import com.match.test.response.ResponseMessage;
import com.match.test.service.IDataService;

import me.xdrop.fuzzywuzzy.FuzzySearch;

@Service
public class DataServiceImpl implements IDataService {

	@Autowired
	private IDataModelRepository modelRepo;

	/**
	 * This method is to add big 'Data' inside 'Database' using for loop In this
	 * method 'Aadhar-Card','Pancard' and 'Id' will be unique every time but name
	 * will be same every time. If we want to add different name then we need to change
	 * 'name'
	 */
	@Override
	public String addDataModel(String name) {

		DataModel2 dataModel = new DataModel2();
		long aadhar = 100030100030L;
		int pan = 1;

		for (int i = 0; i < 2; i++) {
			dataModel.setAadharCard(aadhar + "");
			dataModel.setName(name);
			dataModel.setPanCard("INK" + pan + "SRR");

			modelRepo.save(dataModel);
			aadhar++;
			pan++;
		}
		return "Data Added Successfully";
		
	}

	/**
	 * This method contains implementation of search a 'Pancard' using MySQL "LIKE"
	 * Keyword and Fuzzy search. Here we'll store 'Pancard' similar to our input
	 * 'Pancard' with 70% similarities or more
	 */
	@Override
	public List<String> searchPanCard(String panCard) {
		List<String> panCardList = modelRepo.findByPlaceContaining(panCard);
		List<String> similarPanList = new ArrayList<>();
		int count = 0;

		while (count != panCardList.size()) {
			int matchPercentage = FuzzySearch.ratio(panCardList.get(count), panCard);
			if (70 <= matchPercentage) {
				similarPanList.add(panCardList.get(count));
			}
			count++;
		}
		return similarPanList;
	}

	/**
	 * This method is to search similar 'Names' like 'INPUT' with 'Fuzzy' algo and
	 * It will return a list of 'Names' similar to our 'INPUT'
	 */
	@Override
	public List<String> searchName(String name) {

		List<DataModel2> modelList = modelRepo.findAll();
		int count = 0;
		List<String> matchingNameList = new ArrayList<>();
		while (count != modelList.size()) {
			int matchPercentage = FuzzySearch.ratio(modelList.get(count).getName(), name.trim());
			if (70 <= matchPercentage) {
				matchingNameList.add(modelList.get(count).getName());
			}
			count++;
		}
		return matchingNameList;

	}

	/**
	 * This method is to search similar 'Names' and 'PandCard' like 'INPUT' we got
	 * from postman with 'Fuzzy' algo and It will return a list of 'Name' and
	 * 'PanCard' similar to our 'INPUT'
	 */
	@Override
	public List<String> searchByNameAndPan(DataModel2 model) {

		List<String> modelList = new ArrayList<>();
		modelRepo.findAll().forEach((e) -> {
			int matchRatio = FuzzySearch.ratio(model.getName().trim() + model.getPanCard().trim(),
					e.getName().substring(0, 3) + e.getPanCard().substring(0, 5));
			System.out.println(e.getName().substring(0, 3) + e.getPanCard().substring(0, 5));
			if (matchRatio >= 80) {
				modelList.add("Name : " + e.getName() + " PanCard : " + e.getPanCard());
			}
		});

		return modelList;
	}

	/**
	 * This method has implementation of 'nullCheckForPancard()'
	 */
	public ResponseMessage nullCheckForPancard(String pan, String responseMessage) {

		ResponseMessage responseMessage2 = new ResponseMessage();

		if (pan == null) {
			responseMessage2.setMessage("Pancard Cannot be Empty");
			return responseMessage2;
		} else {
			responseMessage2.setMessage(responseMessage);
			return responseMessage2;
		}

	}

	/**
	 * This method has implementation of a method 'nullCheckForPancardAndAadharCard'
	 * to check Pan-Card and Aadhar-Card Cannot be Empty(null)
	 */
	public ResponseMessage nullCheckForPancardAndAadharCard(String pan, String aadharCard, String responseMessage) {

		ResponseMessage responseMessage2 = new ResponseMessage();

		if (pan == null || aadharCard == null) {
			responseMessage2.setMessage("Pancard or AadharCard Cannot be Empty");
			return responseMessage2;
		} else {
			responseMessage2.setMessage(responseMessage);
			return responseMessage2;
		}
	}

	/**
	 * This method has implementation of 'findByPancardAndAadharCard()' method
	 * available inside 'IDataService'
	 */
	@Override
	public DataModel2 findByPancardAndAadharCard(String pancard, String aadharCard) {

		return modelRepo.findByPanCardAndAadharCard(pancard, aadharCard);
	}
}
