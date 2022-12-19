package com.match.test.service;

import java.util.List;

import com.match.test.entity.DataModel2;

public interface IDataService {

	String searchPanCard(String panCard);

	List<String> searchName(String name);

	void addDataModel();


}
