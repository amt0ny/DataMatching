package com.match.test.service;

import java.util.List;

import org.aspectj.util.FuzzyBoolean;
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
	public void addDataModel(DataModel2 dataModel) {

		modelRepo.save(dataModel);

	}

	@Override
	public List<String> searchPanCard(String panCard) {
		List<String> panCardList = modelRepo.findByPlaceContaining(panCard);
		System.out.println(panCardList);
		
		int count = 0;
		int bestMatch = 0;
		String perfectMatch = null;
		
		while (count != panCardList.size()) {
			int matchPercentage = FuzzySearch.ratio(panCardList.get(count), panCard);
			if (bestMatch<matchPercentage) {
				bestMatch = matchPercentage;
				perfectMatch = panCardList.get(count);
			}
			count++;
		}
		System.out.println(bestMatch + "  " + perfectMatch );
		return panCardList;
	}

	@Override
	public List<DataModel2> findAll() {
		return modelRepo.findAll();
	}

}
