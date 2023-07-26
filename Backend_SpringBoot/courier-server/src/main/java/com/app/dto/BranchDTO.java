package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BranchDTO {
	private Long id;
	@NotBlank(message = "Branch name must be supplied")
	private String branchName;
	@NotBlank(message = "Location must be supplied")
	private String location;
	@NotBlank(message = "Pincode must be supplied")
	private String pincode;
}
