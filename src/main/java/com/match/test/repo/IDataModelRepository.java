package com.match.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.match.test.entity.DataModel2;


@Repository
public interface IDataModelRepository extends JpaRepository<DataModel2, Integer>{

	List<DataModel2> findByPanCard(String panCard);
	
	 @Query("Select d.panCard from DataModel2 d where d.panCard like %:panCard%")
     List<String> findByPlaceContaining(@Param("panCard")String panCard);

	

}
