package com.app.service;

import java.util.List;

import com.app.dto.BranchDTO;
import com.app.entities.BranchEntity;

public interface IBranchService {
	BranchEntity insertBranchDetails(BranchEntity transientBranch);
	BranchDTO upadteBranchDetails(BranchEntity detachedBranch);
	String deleteBranchDetails(long branchId);
	List<BranchDTO> getAllBranches();
	BranchDTO getABranchDetails(long branchId);
}
