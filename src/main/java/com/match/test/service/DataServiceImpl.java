package com.match.test.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.match.test.entity.DataModel2;
import com.match.test.repo.IDataModelRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;

@Service
public class DataServiceImpl implements IDataService {

	@Autowired
	private IDataModelRepository modelRepo;

	/**
	 * This method is to add big 'Data' inside 'Database' using for loop In this
	 * method 'Aadhar-Card','Pancard' and 'Id' will be unique every time but name
	 * will be same every If we want to add different name then we need to change
	 * 'name'
	 */
	@Override
	public void addDataModel() {

		DataModel2 dataModel = new DataModel2();
		long aadhar = 100010100030L;
		int id = 100030;
		int pan = 0;

		for (int i = 0; i < 20000; i++) {
			dataModel.setId(id);
			dataModel.setAadharCard(aadhar + "");
			dataModel.setName("Dhananjay");
			dataModel.setPanCard("INK" + pan + "PAN");

			modelRepo.save(dataModel);

			id++;
			aadhar++;
			pan++;
		}
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
		int bestMatch = 0;
		String perfectMatch = null;

		while (count != panCardList.size()) {
			int matchPercentage = FuzzySearch.ratio(panCardList.get(count), panCard);
			if (70 < matchPercentage) {
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
		int bestMatch = 0;
		String perfectName = null;
		List<String> matchingNameList = new ArrayList<>();
		while (count != modelList.size()) {
			int matchPercentage = FuzzySearch.ratio(modelList.get(count).getName(), name);
			if (70 < matchPercentage) {
				matchingNameList.add(modelList.get(count).getName());
			}
			count++;
		}

		return matchingNameList;

	}

	/**
	 * This method is to search similar 'Names' and 'PandCard' like 'INPUT' we got from postman with 'Fuzzy' algo and
	 * It will return a list of 'Name' and 'PanCard' similar to our 'INPUT'
	 */
	@Override
	public List<String> searchByNameAndPan(DataModel2 model) {
		List<String> modelList = new ArrayList<>();
		String bestNameMatch;
		String bestPancardMatch;
		modelRepo.findAll().forEach((e)->{
			String name = e.getName().substring(0, 3);
			String panCard = e.getPanCard().substring(0, 5);
			int matchRatio =FuzzySearch.ratio(model.getName()+model.getPanCard(), name+ panCard);
			
			if (matchRatio>=80 && matchRatio != 100) {
				modelList.add("Name : "+e.getName()+" PanCard : "+e.getPanCard());
			}
		});
		
		return modelList;
	}
	
	
	
	
	
	
	

}
