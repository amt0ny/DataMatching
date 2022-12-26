package com.match.data.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.match.data.entity.DataModel2;

@Repository
public interface IDataModelRepository extends JpaRepository<DataModel2, Integer> {

	/**
	 * This method is to find 'Pancard' using MySQL 'LIKE' keyword
	 */
	@Query("Select d.panCard from DataModel2 d where d.panCard like %:panCard%")
	List<String> findByPlaceContaining(@Param("panCard") String panCard);
	
	/**
	 * This is the method also coming from 'JpaRepository' to find User-data using both 'PanCard' and 'AadharCard'
	 */
	DataModel2 findByPanCardAndAadharCard(@Param("panCard") String panCard, @Param("aadharCard") String aadharCard);

}
