package com.app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.IBranchRepo;
import com.app.dao.IEmployeeRepository;
import com.app.dto.EmployeeDTO;
import com.app.dto.LoginRequestDTO;
import com.app.dto.RoleEnum;
import com.app.entities.BranchEntity;
import com.app.entities.EmployeeEntity;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired 
	private IEmployeeRepository empRepo;
	
	@Autowired
	private IBranchRepo branchRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public EmployeeDTO authenticateEmployee(LoginRequestDTO request) {
		EmployeeEntity empEntity = empRepo.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials!!!"));
		return mapper.map(empEntity, EmployeeDTO.class);
	}

//	@Override
//	public EmployeeEntity insertEmployeeDetails(EmployeeEntity transientEmp) {
//
//		BranchEntity branch = branchRepo.findById(transientEmp.getBranch().getId()).get();
//		transientEmp.setBranch(branch);
//		transientEmp=empRepo.save(transientEmp);
//		transientEmp.setPassword(encoder.encode(transientEmp.getPassword()));
//		return transientEmp;
//	}
	
	@Override
	   public EmployeeEntity insertEmployeeDetails(EmployeeEntity transientEmp, long branchId) {

	       BranchEntity branch = branchRepo.findById(branchId).get();
	       transientEmp.setBranch(branch);
	       transientEmp=empRepo.save(transientEmp);
	       transientEmp.setPassword(encoder.encode(transientEmp.getPassword()));
	       return transientEmp;
	   }

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeEntity> empList = empRepo.findAll();
		List<EmployeeDTO> empDto=new ArrayList<EmployeeDTO>();
		for(EmployeeEntity empList1:empList ) 
			empDto.add( mapper.map(empList1, EmployeeDTO.class));
		
		return empDto;
	}

	@Override
	public EmployeeDTO getAnEmployeeDetails(long empId) {
		EmployeeEntity emp = empRepo.findById(empId).get();
		EmployeeDTO empDto = new EmployeeDTO();
		empDto = mapper.map(emp, EmployeeDTO.class);
		return empDto;
	}

	@Override
	public EmployeeDTO updateEmployeeDetails(EmployeeEntity detachedEmp) {
		empRepo.findById(detachedEmp.getId()).get();
		BranchEntity branch = branchRepo.findById(detachedEmp.getBranch().getId()).get();
		detachedEmp.setBranch(branch);
		EmployeeEntity emp = empRepo.save(detachedEmp);
		EmployeeDTO empDto = new EmployeeDTO();
		empDto = mapper.map(emp, EmployeeDTO.class);
		return empDto;
	}

	@Override
	public String deleteEmployeeDetails(long empId) {
		String mesg = "Deleting employee details failed!!!";
		if(empRepo.existsById(empId))
		{
			empRepo.deleteById(empId);
			mesg = "Deleted employee details of employee of id " + empId;
		}
		return mesg;
	}

	@Override
	public List<EmployeeEntity> getAllDeliveryBoysOfABranch(long branchId, RoleEnum valueOf) {
		List<EmployeeEntity> employeeList = empRepo.findByRoleAndBranchId(RoleEnum.valueOf("DELIVERY_BOY"), branchId);
		return employeeList;
	}	
	
	@Override
	public EmployeeEntity getByEmail(String email) {
		return empRepo.findByEmail(email);
	}
	
	
}
