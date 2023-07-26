package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
	//sign up a customer 
		//Done with inherited methods
	CustomerEntity findByEmail(String email);
}
