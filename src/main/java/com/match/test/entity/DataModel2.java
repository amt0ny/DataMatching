package com.match.test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DataModel2 {

	/**
	 * Data attributes
	 */
	@Id
	private Integer id;
	private String name;
	private String panCard;
	private String aadharCard;

}
