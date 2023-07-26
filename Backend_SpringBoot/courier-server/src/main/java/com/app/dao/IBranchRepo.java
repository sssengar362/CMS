package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.BranchEntity;

public interface IBranchRepo extends JpaRepository<BranchEntity, Long> {
	//add branch 
		//Done with inherited methods
	//update branch
		//Done with inherited methods
	//delete a branch 
		//Done with inherited methods
	//get all branches
		//Done with inherited methods
	//get a branch details
		//Done with inherited methods
	//get a branch from pincode
		BranchEntity findByPincode(String pincode);
}
