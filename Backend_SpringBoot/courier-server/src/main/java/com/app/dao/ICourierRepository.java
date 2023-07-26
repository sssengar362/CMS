package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CourierEntity;
import com.app.entities.StatusEnum;

public interface ICourierRepository extends JpaRepository<CourierEntity, Long>{

	
	//get all couriers irrespective of branch for admin
		//Done with inherited methods
	//get an courier details irrespective of branch for admin
		//Done with inherited methods
	//add a courier
		//done with inherited methods
	//get all couriers of a customer
		List<CourierEntity> findByCustomerCouriersId(long customerId);	
	//get a courier detail by courierId
		//Done with inherited method
	//update courier status by delivery boy
		//Done with inherited method
	//get all couriers by employee id
		//Done with inherited methods
		List<CourierEntity> findByAllotedToDeliveryBoyId(long empId);
	//get a courier detail by courierId by deliveryBoy
		//Done with inherited methods
	//get all couriers which are in transit state 
		List<CourierEntity> findByStatus(StatusEnum valueOf);
	//allot courier to respective branch_admin via reciever's pincode by admin
		//Done with inherited method
	//get all couriers to be picked up/delivered by branch admin
		List<CourierEntity> findByAllotedToDeliveryBoyIdAndStatus(long empId, StatusEnum valueOf);
	//allot courier to one of delivery boy of a branch by branch admin to pickUp
		//Done with inherited method
}	
