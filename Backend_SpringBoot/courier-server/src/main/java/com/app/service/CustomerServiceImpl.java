package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ICustomerRepository;
import com.app.dto.RoleEnum;
import com.app.entities.CustomerEntity;
import com.app.entities.EmployeeEntity;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public CustomerEntity insertCustomerDetails(CustomerEntity transientCustomer) {
		transientCustomer.setRole(RoleEnum.valueOf("CUSTOMER"));
		transientCustomer.setPassword(encoder.encode(transientCustomer.getPassword()));
		return customerRepo.save(transientCustomer);
	}

	@Override
	public CustomerEntity getByEmail(String email) {
		return customerRepo.findByEmail(email);
	}
}
