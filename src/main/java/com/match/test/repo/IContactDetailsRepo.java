package com.match.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.match.test.entity.ContactDetailsLead;

public interface IContactDetailsRepo extends JpaRepository<ContactDetailsLead, Long>{

}
