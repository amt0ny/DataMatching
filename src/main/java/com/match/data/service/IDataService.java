package com.match.data.service;

import java.util.List;

import com.match.data.entity.DataModel2;
import com.match.data.response.ResponseMessage;


/**
 * This Interface contains all abstract methods of 'DataServiceImpl' class
 */

public interface IDataService {

	/**
	 * This method is to search similar Users inside Database with the help of 'Pan-card'
	 */
	public List<String> searchPanCard(String panCard);

	/**
	 * This method is to search similar Users inside Database with the help of 'name'
	 */
	public List<String> searchName(String name);
	
	/**
	 * This method is to add User data
	 */
	public String addDataModel(String name);
	
	/**
	 * This method is to search User inside Database with the help of Pan and Aadhar-Card
	 */
	public List<String> searchByNameAndPan(DataModel2 model);
	
	/**
	 * Method to search User-Data inside Database using 'Pan-card and 'Aadhar-card'
	 */
	public DataModel2 findByPancardAndAadharCard(String pancard, String AadharCard);
	
	/**
	 * This method is to send response message whether 'Pan-card' is 'null' or not
	 */
	public ResponseMessage nullCheckForPancard(String pan, String response);
	
	/**
	 * This will make sure that Pan-Card and Aadhar-Card Cannot be Empty(null)
	 * 
	 */
	public  ResponseMessage nullCheckForPancardAndAadharCard(String pan, String aadharCard, String responseMessage);



}
