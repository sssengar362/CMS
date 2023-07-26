package com.app.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.app.entities.CategoryEnum;


import com.app.entities.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourierDTO {
	private Long id;
	@NotBlank
	private CustomerDTO customerCouriers;
	
	@NotBlank
	private String sendersAddress;
	
	@NotBlank
	private String sendersPincode;
	
	@NotBlank
	private String receiversName;
	
	@NotBlank
	private String receiversAddress;
	
	@NotBlank
	private String receiversPincode;
	
	@NotBlank
	private EmployeeDTO allotedToDeliveryBoy;
	
	@NotBlank
	private Date bookedDate;
	
	@NotBlank
	private StatusEnum status ;
	
	@NotBlank
	private CategoryEnum category;
	
	@NotBlank
	private double weight;
	
	@NotBlank
	private double amount;
}
