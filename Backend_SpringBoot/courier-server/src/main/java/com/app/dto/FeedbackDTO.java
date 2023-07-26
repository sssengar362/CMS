package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.entities.CustomerEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedbackDTO {
	
	private long id;
	
	@NotBlank
	private String message;
	
	@NotBlank
	private CustomerEntity customerFeedback;
}
