package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dto.RoleEnum;
import com.app.entities.EmployeeEntity;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	//add a finder method for login
	Optional<EmployeeEntity> findByEmailAndPassword(String em, String pass);

	//add an employee 
		//Done with inherited method
	//get all employees
		//Done with inherited method
	//get  an employee details
		//Done with inherited methods
	//update employee details
		//Done with inherited method
	//delete employee by id
		//Done with inherited menthod
	//get all delivery boys of a particular branch(By branchId)
		//Done with inherited methods
		//List<EmployeeEntity> findByBr
	//get an employee from beanchId and role="BRANCH_ADMN"
		EmployeeEntity findByBranchIdAndRole(Long id, RoleEnum valueOf);
	//get all delivery boys of a particular branch
		List<EmployeeEntity> findByRoleAndBranchId(RoleEnum valueOf, Long id);
		
		EmployeeEntity findByEmail(String email);
		
}
