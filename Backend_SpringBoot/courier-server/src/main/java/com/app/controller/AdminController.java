package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.BranchDTO;
import com.app.dto.CourierDTO;
import com.app.dto.EmployeeDTO;
import com.app.dto.FeedbackDTO;
import com.app.dto.LoginRequestDTO;
import com.app.entities.BranchEntity;
import com.app.entities.CourierEntity;
import com.app.entities.EmployeeEntity;
import com.app.entities.StatusEnum;
import com.app.service.IBranchService;
import com.app.service.ICourierService;
import com.app.service.IEmployeeService;
import com.app.service.IFeedbackService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*")
@Validated
@Slf4j
public class AdminController {

	@Autowired
	private IEmployeeService empService;
	
	@Autowired
	private ICourierService courierService;
	
	@Autowired
	private IBranchService branchService;
	
	@Autowired
	private IFeedbackService feedbackService;
	
//	@PostMapping("/addEmployee")
//	public ResponseEntity<?> addNewEmployee(@RequestBody @Valid EmployeeEntity emp) {
//		try {
//			System.out.println("in add new emp " + emp.getId());// id : null
//
//			return ResponseEntity.status(HttpStatus.CREATED).body(empService.insertEmployeeDetails(emp));
//		} catch (RuntimeException e) {
//			System.out.println("in add new emp err " + e);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
//		}
//	}
	@PostMapping("/addEmployee/{bId}")
	public ResponseEntity<?> addNewEmployee(@RequestBody @Valid EmployeeEntity emp, @PathVariable  @Valid long bId ) {
		try {
			System.out.println("in add new emp " + emp.getId());// id : null

			return ResponseEntity.status(HttpStatus.CREATED).body(empService.insertEmployeeDetails(emp,bId));
		} catch (RuntimeException e) {
			System.out.println("in add new emp err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateEmployee(@RequestBody @Valid LoginRequestDTO request)
//	{
//		System.out.println("in auth emp " + request);
//		return ResponseEntity.ok().body(empService.authenticateEmployee(request));
//	}
	
	@GetMapping("/getAllEmployees")
	public List<EmployeeDTO> fetchAllEmployeeDetails() {
		System.out.println("in fetch all ");
		return empService.getAllEmployees();
	}
	
	@GetMapping("/getEmployeeDetails/{empId}")
	public ResponseEntity<?> getEmployeeDetails(@PathVariable @Valid long empId)
	{	
		return new ResponseEntity<>(empService.getAnEmployeeDetails(empId), HttpStatus.OK);
	}
	
	@PutMapping("/updateEmployeeDetails")
	public EmployeeDTO updateEmployeeDetails(@RequestBody EmployeeEntity emp)
	{
		System.out.println("in update  emp dtls" + emp);
		return empService.updateEmployeeDetails(emp);
	}
	
	@DeleteMapping("/deleteEmpById/{eId}")
	public String deleteEmployeeDetails(@PathVariable @Valid long eId) {
		System.out.println("in del emp dtls " + eId);
		return empService.deleteEmployeeDetails(eId);	
	}
	
	@PostMapping("/addBranch")
	public ResponseEntity<?> addNewEmployee(@RequestBody @Valid BranchEntity branch) {
		try {
			System.out.println("in add new emp " + branch.getId());// id : null

			return ResponseEntity.status(HttpStatus.CREATED).body(branchService.insertBranchDetails(branch));
		} catch (RuntimeException e) {
			System.out.println("in add new branch err " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/updateBranchDetails")
	public BranchDTO updateBranchDetails(@RequestBody BranchEntity branch)
	{
		System.out.println("in update  branch dtls" + branch);
		return branchService.upadteBranchDetails(branch);
	}
	
	@DeleteMapping("/deleteByBranchId/{bId}")
	public String deleteBranchDetails(@PathVariable @Valid long bId) {
		System.out.println("in del emp dtls " + bId);
		return branchService.deleteBranchDetails(bId);	
	}
	
	@GetMapping("/getAllBranches")
	public List<BranchDTO> fetchAllBranchDetails() {
		System.out.println("in fetch all of branch");
		return branchService.getAllBranches();
	}
	
	@GetMapping("/getBranchById/{bId}")
	public ResponseEntity<?> getBranchDetails(@PathVariable @Valid long bId)
	{
		return new ResponseEntity<>(branchService.getABranchDetails(bId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllCouriers")
	public List<CourierEntity> fetchAllCourierDetails() {
		System.out.println("in fetch all of courier");
		return courierService.getAllCouriers();
	}
	
	@GetMapping("getCourierById/{cId}")
	public ResponseEntity<?> getCourierDetails(@PathVariable @Valid long cId)
	{
		return new ResponseEntity<>(courierService.getAnCourierDetails(cId), HttpStatus.OK);
	}
	
	@GetMapping("/getAllFeedbacks")
	public List<FeedbackDTO> fetchAllFeedbackDetails() {
		System.out.println("in fetch all of feedback");
		return feedbackService.getAllFeedbacks();
	}
	
	@GetMapping("/getAllCouriersInTransitState")
	public List<CourierEntity> fetchAllCouriersInTransitState() {
		return courierService.getAllCouriersInTransitState(StatusEnum.valueOf("IN_TRANSIT"));
	}
	
	@PutMapping("/alotCourierToRecieverPincodeBranchAdminAndChangeStatus/{courierId}")
	public CourierEntity alotCourierToRespectiveBranchAdmin(@PathVariable @Valid long courierId) {
		return courierService.alotACourierToBranchAdminAndChangeStatusWhichAreInTransitState(courierId);
	}
	
}
