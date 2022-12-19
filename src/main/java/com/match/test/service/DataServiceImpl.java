package com.match.test.service;

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

	@Override
	public String searchPanCard(String panCard) {
		List<String> panCardList = modelRepo.findByPlaceContaining(panCard);

		System.out.println(panCardList);
		int count = 0;
		int bestMatch = 0;
		String perfectMatch = null;

		while (count != panCardList.size()) {
			int matchPercentage = FuzzySearch.ratio(panCardList.get(count), panCard);
			if (bestMatch < matchPercentage) {
				bestMatch = matchPercentage;
				perfectMatch = panCardList.get(count);
			}
			count++;
		}
		return perfectMatch;
	}

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

}
