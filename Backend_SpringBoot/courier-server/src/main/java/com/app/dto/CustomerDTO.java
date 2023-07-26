package com.app.dto;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.app.entities.CourierEntity;
import com.app.entities.FeedbackEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
public class CustomerDTO {
	private Long id;
	@NotBlank(message = "First Name must be supplied")
	@Length(min = 5,max=20, message = "Invalid length of chars for  first name")
	private String firstName;
	
	@NotBlank(message = "Last Name must be supplied")
	@Length(min = 5,max=20, message = "Invalid length of chars for  first name")
	private String lastName;
	
	@NotBlank(message = "Email must be supplied ...")
	@Email(message = "Invalid Email Format")
	private String email;
	
	private RoleEnum role;
	
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Blank or invalid password")
	//password should it added during :1.  serialization(java->json) 2. de- serialization(json-->java) 
	//Ans : 2
	@JsonProperty(access =Access.WRITE_ONLY)
	private String password;
	
}
