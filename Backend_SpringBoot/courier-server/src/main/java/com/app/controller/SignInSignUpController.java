package com.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.entities.CustomerEntity;
import com.app.entities.EmployeeEntity;
//import com.app.dto.UserDTO;
import com.app.jwt_utils.JwtUtils;
import com.app.service.CustomerServiceImpl;
import com.app.service.EmployeeServiceImpl;
//import com.app.service.UserService;
import com.app.service.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class SignInSignUpController {
//dep : JWT utils : for generating JWT
	@Autowired
	private JwtUtils utils;
	
	@Autowired
	private ICustomerService customerService;
	
	// dep : Auth mgr
	@Autowired
	private AuthenticationManager manager;
	//dep : user service for handling users
	
	@Autowired
	private EmployeeServiceImpl employeeService;

	// add a method to authenticate user . In case of success --send back token , o.w
	// send back err mesg
	@PostMapping("/signin")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request) {
		// store incoming user details(not yet validated) into Authentication object
		// Authentication i/f ---> implemented by UserNamePasswordAuthToken
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		log.info("auth token before {}",authToken);
		try {
			// authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
			log.info("auth token again {} " , authenticatedDetails);
			// => auth succcess
			
			
			
			
			//////////////////////////////////  updated--17/09/22   //////////////////////////////////////////////////////////////
			
//			return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails)));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "Auth successful");
			map.put("token",utils.generateJwtToken(authenticatedDetails));
			EmployeeEntity authEmp= employeeService.getByEmail(request.getEmail());
			CustomerEntity authCustomer = customerService.getByEmail(request.getEmail());
			if (authEmp!=null) {
				map.put("user", authEmp);
				map.put("id", authEmp.getId());
				map.put("role", authEmp.getRole());
				return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails),authEmp,null));
			} else //if (authCustomer!=null)
				{
				map.put("user", authCustomer);
				map.put("id", authCustomer.getId());
				map.put("role", authCustomer.getRole());
				return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails),null,authCustomer));
				}
		} catch (BadCredentialsException e) {
			// send back err resp code
			System.out.println("err "+e);
			Map<String, Object> errorMap = new HashMap<String, Object>();
			errorMap.put("status","INVALID CREDENTIALS");
			return ResponseEntity.ok(errorMap);
		}

	}
	//add request handling method for user registration
	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@RequestBody @Valid CustomerEntity customer)
	{
		System.out.println("in reg customer : customer "+ customer +" roles "+ customer.getRole());//{....."roles" : [ROLE_USER,...]}
		//invoke service layer method , for saving : user info + associated roles info
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.insertCustomerDetails(customer));		
	}
}

