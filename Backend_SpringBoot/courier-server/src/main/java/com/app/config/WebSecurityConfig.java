package com.app.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filters.JWTRequestFilter;

@EnableWebSecurity // mandatory
@Configuration // mandatory
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private JWTRequestFilter filter;

	// configure BCryptPassword encoder bean
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().
		exceptionHandling().
		authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		}).
		and().
		authorizeRequests()
//		.antMatchers("/products/add").hasRole("ADMIN")
//		.antMatchers("/products/purchase").hasRole("CUSTOMER")
		.antMatchers("/auth/signin").permitAll() // enabling global
		.antMatchers("/auth/signup").permitAll()
		.antMatchers("/track/**").permitAll()
																										// access to all
																										// urls with
																										// /auth
				// only required for JS clnts (react / angular)
		.antMatchers("/admin/addEmployee/addEmployee").hasRole("ADMIN")
		.antMatchers("/admin/getAllEmployees").hasRole("ADMIN")
		.antMatchers("/admin/getEmployeeDetails").hasRole("ADMIN")
		.antMatchers("/admin/updateEmployeeDetails").hasRole("ADMIN")
		.antMatchers("/admin/deleteEmpById").hasRole("ADMIN")
		.antMatchers("/admin/addBranch").hasRole("ADMIN")
		.antMatchers("/admin/updateBranchDetails").hasRole("ADMIN")
		.antMatchers("/admin/deleteByBranchId").hasRole("ADMIN")
		.antMatchers("/admin/getAllBranches").hasRole("ADMIN")
		.antMatchers("/admin/getBranchById").hasRole("ADMIN")
		.antMatchers("/admin/getAllCouriers").hasRole("ADMIN")
		.antMatchers("/admin/getCourierById").hasRole("ADMIN")
		.antMatchers("/admin/getAllFeedbacks").hasRole("ADMIN")
		.antMatchers("/admin/getAllCouriersInTransitState").hasRole("ADMIN")
		.antMatchers("/admin/alotCourierToRecieverPincodeBranchAdminAndChangeStatus").hasRole("ADMIN")
		
		.antMatchers("/branchAdmin/getAllDeliveryBoysOfABranch").hasRole("BRANCH_ADMIN") //get branchId from logged in user details
		.antMatchers("/branchAdmin/getAllOrdersToBePickedUp").hasRole("BRANCH_ADMIN") //get empId from logged in user details
		.antMatchers("/branchAdmin/alotCourierToOneOfDeliveryBoyToPickUp").hasRole("BRANCH_ADMIN")
		.antMatchers("/branchAdmin/getAllOrdersToBeDelivered").hasRole("BRANCH_ADMIN") //get empId from logged in user details
		.antMatchers("/branchAdmin/alotCourierToOneOfDeliveryBoyToDeliver").hasRole("BRANCH_ADMIN")
		
		.antMatchers("/deliveryBoy/getAllCouriersByEmpId").hasRole("DELIVERY_BOY") //get empId from logged in user details
		.antMatchers("/deliveryBoy/getACourierDetailByCourierId").hasRole("DELIVERY_BOY")
		.antMatchers("/deliveryBoy/getAllOrdersToBePickedUp").hasRole("DELIVERY_BOY") //get empId from logged in user details
		.antMatchers("/deliveryBoy/getAllOrdersToBeDeliver").hasRole("DELIVERY_BOY") //get empId from logged in user details
		.antMatchers("/deliveryBoy/updateCourierStatusToPickedUp").hasRole("DELIVERY_BOY")
		.antMatchers("/deliveryBoy/updateCourierStatusToInTransit").hasRole("DELIVERY_BOY")
		.antMatchers("/deliveryBoy/updateCourierStatusToDelivered").hasRole("DELIVERY_BOY")
		.antMatchers("/deliveryBoy/updateCourierStatusToUnsuccessfulDelivery").hasRole("DELIVERY_BOY")
		
		.antMatchers("/customer/addCourier").hasRole("CUSTOMER")
	    .antMatchers("/customer/getAllCouriersById").hasRole("CUSTOMER") //get custId from logged in user details
		//.antMatchers("/customer/**").hasRole("CUSTOMER")
		.antMatchers("/customer/getACourierDetailsByCourierId").hasRole("CUSTOMER")
//		.antMatchers("/customer/signup").hasRole("CUSTOMER")
		.antMatchers("/customer/addFeedback").hasRole("CUSTOMER")
		
		
		
		.antMatchers(HttpMethod.OPTIONS).permitAll().
		anyRequest().authenticated().
		and().
//		and().
		csrf().disable().
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// configure auth mgr bean : to be used in Authentication REST controller
	@Bean
	public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
