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

	@Id
	private Integer id;
	private String name;
	private String panCard;
	private String aadharCard;
//
//	public DataModel2(String panCard) {
//		super();
//		this.panCard = panCard;
//	}

}