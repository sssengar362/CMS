package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.CourierEntity;
import com.app.entities.CustomerEntity;
import com.app.entities.FeedbackEntity;
import com.app.service.ICourierService;
import com.app.service.ICustomerService;
import com.app.service.IFeedbackService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
@Validated
@Slf4j
public class CustomerController {

	@Autowired
	private ICourierService courierService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IFeedbackService feedbackService;
	
//	@PostMapping("/addCourier")
//	public ResponseEntity<?> addNewCourier(@RequestBody @Valid CourierEntity courier) {
//		try {
//			System.out.println("in add new courier " + courier.getId());// id : null
//			return ResponseEntity.status(HttpStatus.CREATED).body(courierService.insertCourierDetails(courier));
//		} catch (RuntimeException e) {
//			System.out.println("in add new courier err " + e);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
//		}
//	}
	@PostMapping("/addCourier/{custId}")
	public ResponseEntity<?> addNewCourier(@PathVariable @Valid long custId,@RequestBody @Valid CourierEntity courier) {
		try {
			System.out.println(courier);
			System.out.println("in add new courier " + courier.getId());// id : null
			return ResponseEntity.status(HttpStatus.CREATED).body(courierService.insertCourierDetails(courier, custId));
		} catch (RuntimeException e) {
			System.out.println("in add new courier err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/getAllCouriersById/{custId}")
	public List<CourierEntity> getAllCouriersByCustomerId(@PathVariable @Valid long custId) {
		return courierService.getCouriersById(custId);
	}
	
	@GetMapping("getACourierDetailsByCourierId/{cId}")
	public ResponseEntity<?> getCourierDetails(@PathVariable @Valid long cId)
	{
		return new ResponseEntity<>(courierService.getCourierDetailsByCourierId(cId), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> addNewCustomer(@RequestBody @Valid CustomerEntity customer) {
		try {
			System.out.println("in add new customer " + customer.getId());// id : null

			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.insertCustomerDetails(customer));
		} catch (RuntimeException e) {
			System.out.println("in add new customer err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
//	@PostMapping("/addFeedback/{custId}")
//	public ResponseEntity<?> addNewFeedback(@RequestBody String message,@PathVariable @Valid long custId) {
//		
//		
//		try {
//			
//
//			return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.insertFeedbackDetails(message, custId));
//		} catch (RuntimeException e) {
//			System.out.println("in add new feedback err " + e);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
//		}
//	}
	@PostMapping("/addFeedback")
	public ResponseEntity<?> addNewFeedback(@RequestParam("id") long customerId, @RequestParam("message") String message) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.insertFeedbackDetails(customerId, message));
		} catch (RuntimeException e) {
			System.out.println("in add new feedback err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	
}
