package com.app.service;

import java.util.List;

import com.app.dto.CourierDTO;
import com.app.entities.CourierEntity;
import com.app.entities.StatusEnum;

public interface ICourierService {
	List<CourierEntity> getAllCouriers();
	CourierEntity getAnCourierDetails(long courierId);
	//CourierEntity insertCourierDetails(CourierEntity transientCurier);
	CourierEntity insertCourierDetails(CourierEntity transientCourier, long custId);
	List<CourierEntity> getCouriersById(long custId);
	CourierEntity getCourierDetailsByCourierId(long courierId);
	List<CourierEntity> getAllCouriersByEmpId(long empId);
	CourierEntity updateCourierStatusToPickedUp(long courierId);
	CourierEntity updateCourierStatusToInTransit(long courierId);
	List<CourierEntity> getAllCouriersInTransitState(StatusEnum valueOf);
	CourierEntity alotACourierToBranchAdminAndChangeStatusWhichAreInTransitState(long courierId);//by admin
	List<CourierEntity> getAllOrdersToBePickedUp(long empId, StatusEnum valueOf);
//	CourierEntity alotCourierToOneOfDeliveryBoy(CourierEntity detachedCourier, long empId);	
	List<CourierEntity> getAllOrdersToBeDelivered(long empId, StatusEnum valueOf);
	CourierEntity updateCourierStatusToDelivered(long courierId);
	CourierEntity updateCourierStatusToUnsuccessfulDelivery(long courierId);
	List<CourierEntity> getAllOrdersToBeDeliver(long empId, StatusEnum valueOf);
	public CourierEntity alotCourierToOneOfDeliveryBoy(long dbid, long courierId);
}
