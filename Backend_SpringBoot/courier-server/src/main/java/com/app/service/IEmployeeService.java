package com.app.service;

import java.util.List;

import com.app.dto.EmployeeDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.RoleEnum;
import com.app.entities.EmployeeEntity;

public interface IEmployeeService {
	EmployeeDTO authenticateEmployee(LoginRequestDTO request);
	//EmployeeEntity insertEmployeeDetails(EmployeeEntity transientEmp);
	EmployeeEntity insertEmployeeDetails(EmployeeEntity transientEmp, long branchId);
	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO getAnEmployeeDetails(long empId);
	EmployeeDTO updateEmployeeDetails(EmployeeEntity emp);
	String deleteEmployeeDetails(long empId);
	List<EmployeeEntity> getAllDeliveryBoysOfABranch(long branchId, RoleEnum valueOf);
	public EmployeeEntity getByEmail(String email);
}
