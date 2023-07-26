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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.CourierEntity;
import com.app.entities.StatusEnum;
import com.app.service.IBranchService;
import com.app.service.ICourierService;
import com.app.service.IEmployeeService;
import com.app.service.IFeedbackService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/deliveryBoy")
@CrossOrigin(origins = "*")
@Validated
@Slf4j
public class DeliveryBoyController {

	@Autowired
	private IEmployeeService empService;
	
	@Autowired
	private ICourierService courierService;
	
	@Autowired
	private IBranchService branchService;
	
	@Autowired
	private IFeedbackService feedbackService;
	
	@GetMapping("/getAllCouriersByEmpId/{empId}")
	public ResponseEntity<?> getCouriersByEmployeeId(@PathVariable @Valid long empId)
	{	
		return new ResponseEntity<>(courierService.getAllCouriersByEmpId(empId), HttpStatus.OK);
	}
	
	@GetMapping("/getACourierDetailByCourierId/{courierId}")
	public ResponseEntity<?> getACourierDetails(@PathVariable @Valid long courierId)
	{
		return new ResponseEntity<>(courierService.getAnCourierDetails(courierId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllOrdersToBePickedUp/{empId}")//this empId fetched from session of employee
	public List<CourierEntity> getAllOrdersToBePickedUp(@PathVariable long empId, StatusEnum valueOf) {
		List<CourierEntity> courierList = courierService.getAllOrdersToBePickedUp(empId, StatusEnum.valueOf("READY_FOR_PICKUP"));
		return courierList;
	}
	
	@GetMapping("/getAllOrdersToBeDeliver/{empId}")//this empId fetched from session of employee
	public List<CourierEntity> getAllOrdersToBeDeliver(@PathVariable long empId, StatusEnum valueOf) {
		List<CourierEntity> courierList = courierService.getAllOrdersToBeDelivered(empId, StatusEnum.valueOf("DISPATCHED"));
		return courierList;
	}
	
	@PutMapping("/updateCourierStatusToPickedUp/{courierId}")
	public CourierEntity updateStatusOfCourierToPickedUp(@PathVariable @Valid long courierId) {
		System.out.println("in update of courier dtls" + courierId);
		return courierService.updateCourierStatusToPickedUp(courierId);
	}
	
	@PutMapping("/updateCourierStatusToInTransit/{courierId}")
	public CourierEntity updateStatusOfCourierToInTransit(@PathVariable @Valid long courierId) {
		System.out.println("in update of courier dtls" + courierId);
		return courierService.updateCourierStatusToInTransit(courierId);
	}
	
	@PutMapping("/updateCourierStatusToDelivered/{courierId}")
	public CourierEntity updateStatusOfCourierToDelivered(@PathVariable @Valid long courierId) {
		System.out.println("in update of courier dtls" + courierId);
		return courierService.updateCourierStatusToDelivered(courierId);
	}
	
	@PutMapping("/updateCourierStatusToUnsuccessfulDelivery/{courierId}")
	public CourierEntity updateStatusOfCourierToUnsuccessfulDelivery(@PathVariable @Valid long courierId) {
		System.out.println("in update of courier dtls" + courierId);
		return courierService.updateCourierStatusToUnsuccessfulDelivery(courierId);
	}
}
