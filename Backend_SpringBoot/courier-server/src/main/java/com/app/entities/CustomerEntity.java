package com.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.dto.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "password")
public class CustomerEntity extends BaseEntity implements Serializable {

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
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private RoleEnum role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerFeedback", cascade = CascadeType.ALL)
	private List<FeedbackEntity> feedbacks;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customerCouriers", cascade = CascadeType.ALL)
	private List<CourierEntity> couriers;
	
	
}
