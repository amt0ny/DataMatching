package com.match.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.match.data.entity.ContactDetailsLead;

public interface IContactDetailsRepo extends JpaRepository<ContactDetailsLead, Long>{

}
