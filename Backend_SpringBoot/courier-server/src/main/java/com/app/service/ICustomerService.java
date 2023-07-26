package com.app.service;

import com.app.entities.CustomerEntity;

public interface ICustomerService {
	CustomerEntity insertCustomerDetails(CustomerEntity transientCustomer);
	public CustomerEntity getByEmail(String email);
}
