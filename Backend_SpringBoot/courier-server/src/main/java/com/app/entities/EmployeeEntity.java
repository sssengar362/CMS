package com.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.app.dto.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString//(exclude = "password")

public class EmployeeEntity extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 20, unique = true)
	private String email;
	@Column(length = 350)
	private String password;
	@Column(length = 13)
	private String mobileNo;
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private RoleEnum role;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branch_id")
	private BranchEntity branch;
	
	@JsonIgnore
	@OneToMany(mappedBy = "allotedToDeliveryBoy", cascade = CascadeType.ALL) //or persist
	private List<CourierEntity> couriers;

}
