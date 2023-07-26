package com.app.entities;


import java.time.LocalDateTime;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class CourierEntity extends BaseEntity{
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private CustomerEntity customerCouriers;
	
	@Column(length = 100)
	private String sendersAddress;
	@Column(length = 6)
	private String sendersPincode;
	
	@Column(length = 15)
	private String receiversName;
	@Column(length = 100)
	private String receiversAddress;
	@Column(length = 6)
	private String receiversPincode;
	
	@ManyToOne(cascade = CascadeType.ALL) //or persist
	@JoinColumn(name = "employee_id")
	private EmployeeEntity allotedToDeliveryBoy;
	
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date dateOfAdmission;
	
	//to be confirmed
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookedDate;
	
	@Enumerated(EnumType.STRING)
	@Column
	private StatusEnum status ;
	
	@Enumerated(EnumType.STRING)
	@Column
	private CategoryEnum category;
	
	@Column
	private double weight;
	
	@Column
	private double amount;
}
