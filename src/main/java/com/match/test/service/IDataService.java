package com.match.test.service;

import java.util.List;

import com.match.test.entity.DataModel2;

public interface IDataService {

	void addDataModel(DataModel2 dataModel);

	List<String> searchPanCard(String panCard);
	
	List<DataModel2> findAll();

}
