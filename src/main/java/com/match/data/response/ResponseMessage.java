package com.match.data.response;

import com.match.data.response.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage {

	/**
	 * Response message will be sent as a string 
	 */
	private String message;

}
