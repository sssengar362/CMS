package com.app.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entities.CustomerEntity;
import com.app.entities.EmployeeEntity;
//import com.app.entities.UserEntity;

import lombok.ToString;

@ToString
public class CustomUserDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerEntity customer;
	private EmployeeEntity employee;

	public CustomUserDetails(CustomerEntity customer) {
		super();
		this.customer = customer;
	}

	public CustomUserDetails(EmployeeEntity employee) {
		super();
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Meaning : This method should ret Collection(List) of granted authorities ,
		// for a specific user --which will be later stored in Auth obj
		// SimpleGrantedAuthority(String roleName) imple GrantedAuthority
		// UserEntity ---> Role

//		return user.getUserRoles() //Set<Role>
//		 .stream() //Stream<Role>
//		 .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())) //Stream<SimpleGrantedAuthority>
//		 .collect(Collectors.toList());		
		if (employee != null) // user is logged in under Emp role
			return List.of(new SimpleGrantedAuthority("ROLE_"+employee.getRole().name()));
		return List.of(new SimpleGrantedAuthority("ROLE_"+customer.getRole().name()));
	}

	@Override
	public String getPassword() {
		if (employee != null)
			return employee.getPassword();
		return customer.getPassword();
	}

	@Override
	public String getUsername() {
		if (employee != null)
			return employee.getEmail();
		return customer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

//	public String getID() {
//		if (employee != null)
//			return employee.getId().toString();
//		return customer.getId().toString();
//	}

	public String getRole() {
		if (employee != null)
			return employee.getRole().toString();
		return customer.getRole().toString();
		}

}
