package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ICustomerRepository;
import com.app.dao.IEmployeeRepository;
import com.app.entities.CustomerEntity;
import com.app.entities.EmployeeEntity;

import lombok.ToString;

@Service // or @Component also works!
@Transactional

public class CustomUserDetailsService implements UserDetailsService {
	// dep : user repository : based upon spring data JPA
	@Autowired
	private ICustomerRepository customerRepo;
	@Autowired
	private IEmployeeRepository employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("in load by user nm " + email);
		// invoke dao's method to load user details from db by username(ie. actaully an
		// email)
		EmployeeEntity employee = employeeRepo.
				findByEmail(email);
		System.out.println("Employee details from database " + employee);
		CustomerEntity customer = customerRepo
				.findByEmail(email);
		System.out.println("Customer details from database " + customer);
		if(employee == null)
		{
			if(customer != null)
			{
				return new CustomUserDetails(customer);
			}
			else
			{
				throw new UsernameNotFoundException("Invalid email!!!");
			}
		}
		return new CustomUserDetails(employee);
	}

}
