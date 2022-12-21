package com.match.test.service;

import java.util.List;

import com.match.test.entity.DataModel2;

/**
 * This Interface contains all abstract methods of 'DataServiceImpl' class
 */
public interface IDataService {

	public List<String> searchPanCard(String panCard);

	public List<String> searchName(String name);

	public void addDataModel();

	public List<String> searchByNameAndPan(DataModel2 model);


}
