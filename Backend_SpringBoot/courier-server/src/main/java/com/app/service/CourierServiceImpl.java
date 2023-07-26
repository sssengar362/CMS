package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IBranchRepo;
import com.app.dao.ICourierRepository;
import com.app.dao.ICustomerRepository;
import com.app.dao.IEmployeeRepository;
import com.app.dto.CourierDTO;
import com.app.dto.EmployeeDTO;
import com.app.dto.RoleEnum;
import com.app.entities.BranchEntity;
import com.app.entities.CourierEntity;
import com.app.entities.CustomerEntity;
import com.app.entities.EmployeeEntity;
import com.app.entities.StatusEnum;

@Service
@Transactional
public class CourierServiceImpl implements ICourierService {

	@Autowired
	private ICourierRepository courierRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Autowired
	private IEmployeeRepository employeeRepo;
	
	@Autowired
	private IBranchRepo branchRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<CourierEntity> getAllCouriers() {
		List<CourierEntity> courierList = courierRepo.findAll();
//		List<CourierDTO> courierDto=new ArrayList<CourierDTO>();
//		for(CourierEntity c1 : courierList ) 
//			courierDto.add( mapper.map(c1, CourierDTO.class));
		return courierList;
	}

	@Override
	public CourierEntity getAnCourierDetails(long courierId) {
		CourierEntity courier = courierRepo.findById(courierId).get();
//		System.out.println(courier);
//		CourierDTO courierDto = new CourierDTO();
//		courierDto = mapper.map(courier, CourierDTO.class);
//   	System.out.println(courierDto);
		return courier;
	}

//	@Override
//	public CourierEntity insertCourierDetails(CourierEntity transientCourier) {
//		CustomerEntity customer = customerRepo.findById(transientCourier.getCustomerCouriers().getId()).get();
//		transientCourier.setCustomerCouriers(customer);
//		transientCourier.setStatus(StatusEnum.READY_FOR_PICKUP);
//		double weight = transientCourier.getWeight();
//		double amt = weight * 250;
//		transientCourier.setAmount(amt);
//		String pincode = transientCourier.getSendersPincode();
//		BranchEntity branch = branchRepo.findByPincode(pincode);
//		Long id = branch.getId();
//		EmployeeEntity employee = employeeRepo.findByBranchIdAndRole(id, RoleEnum.valueOf("BRANCH_ADMIN"));
//		transientCourier.setAllotedToDeliveryBoy(employee);
//		transientCourier = courierRepo.save(transientCourier);
//		return transientCourier;
//	}
	
	@Override
	public CourierEntity insertCourierDetails(CourierEntity transientCourier, long custId) {
		CustomerEntity customer = customerRepo.findById(custId).get();
		transientCourier.setCustomerCouriers(customer);
		transientCourier.setStatus(StatusEnum.READY_FOR_PICKUP);
		double weight = transientCourier.getWeight();
		double amt = weight * 250;
		transientCourier.setAmount(amt);
		String pincode = transientCourier.getSendersPincode();
		BranchEntity branch = branchRepo.findByPincode(pincode);
		Long id = branch.getId();
		EmployeeEntity employee = employeeRepo.findByBranchIdAndRole(id, RoleEnum.valueOf("BRANCH_ADMIN"));
		transientCourier.setAllotedToDeliveryBoy(employee);
		transientCourier = courierRepo.save(transientCourier);
		return transientCourier;
	}

	@Override
	public List<CourierEntity> getCouriersById(long custId) {
		List<CourierEntity> courierEntity = courierRepo.findByCustomerCouriersId(custId);
//		List<CourierDTO> courierDTO = new ArrayList<CourierDTO>();
//		for(CourierEntity c1 : courierEntity)
//			courierDTO.add(mapper.map(courierEntity, CourierDTO.class));
		return courierEntity;
	}

	@Override
	public CourierEntity getCourierDetailsByCourierId(long courierId) {
		CourierEntity courier = courierRepo.findById(courierId).get();
		return courier;
	}

	@Override
	public List<CourierEntity> getAllCouriersByEmpId(long empId) {
		List<CourierEntity> courierEntity = courierRepo.findByAllotedToDeliveryBoyId(empId);
//		List<CourierDTO> courierDto = new ArrayList<CourierDTO>();
//		for(CourierEntity c1 : courierEntity)
//			courierDto.add(mapper.map(courierEntity, CourierDTO.class));
		return courierEntity;
	}

	@Override
	public CourierEntity updateCourierStatusToPickedUp(long courierId) {
		CourierEntity detachedCourier = courierRepo.findById(courierId).get();
		CustomerEntity customer = customerRepo.findById(detachedCourier.getCustomerCouriers().getId()).get();
		detachedCourier.setCustomerCouriers(customer);
		EmployeeEntity employee = employeeRepo.findById(detachedCourier.getAllotedToDeliveryBoy().getId()).get();
		detachedCourier.setAllotedToDeliveryBoy(employee);
		detachedCourier.setStatus(StatusEnum.valueOf("PICKEDUP"));
		return courierRepo.save(detachedCourier);
	}

	@Override
	public List<CourierEntity> getAllCouriersInTransitState(StatusEnum valueOf) {
		List<CourierEntity> courierList = courierRepo.findByStatus(StatusEnum.valueOf("IN_TRANSIT"));
		return courierList;
	}

	@Override
	public CourierEntity alotACourierToBranchAdminAndChangeStatusWhichAreInTransitState(long courierId) {
		CourierEntity detachedCourier = courierRepo.findById(courierId).get();
		String pincode = detachedCourier.getReceiversPincode();
		BranchEntity branch = branchRepo.findByPincode(pincode);
		Long id = branch.getId();
		EmployeeEntity employee = employeeRepo.findByBranchIdAndRole(id, RoleEnum.valueOf("BRANCH_ADMIN"));
		detachedCourier.setAllotedToDeliveryBoy(employee);
		detachedCourier.setStatus(StatusEnum.valueOf("DISPATCHED"));
		detachedCourier = courierRepo.save(detachedCourier);
		return detachedCourier;
	}

	@Override
	public List<CourierEntity> getAllOrdersToBePickedUp(long empId, StatusEnum valueOf) {
		List<CourierEntity> courierList = courierRepo.findByAllotedToDeliveryBoyIdAndStatus(empId, StatusEnum.valueOf("READY_FOR_PICKUP"));
		return courierList;
	}
	
	@Override
	public List<CourierEntity> getAllOrdersToBeDeliver(long empId, StatusEnum valueOf) {
		List<CourierEntity> courierList = courierRepo.findByAllotedToDeliveryBoyIdAndStatus(empId, StatusEnum.valueOf("DISPATCHED"));
		return courierList;
	}

	@Override
	public CourierEntity alotCourierToOneOfDeliveryBoy(long dbid, long courierId) {
		EmployeeEntity deliveryBoy = employeeRepo.findById(dbid).get();
		CourierEntity couriertoballotted = courierRepo.findById(courierId).get();
		couriertoballotted.setAllotedToDeliveryBoy(deliveryBoy);
		courierRepo.save(couriertoballotted);
		return couriertoballotted;
	}

	@Override
	public CourierEntity updateCourierStatusToInTransit(long courierId) {
		CourierEntity detachedCourier = courierRepo.findById(courierId).get();
		CustomerEntity customer = customerRepo.findById(detachedCourier.getCustomerCouriers().getId()).get();
		detachedCourier.setCustomerCouriers(customer);
		EmployeeEntity employee = employeeRepo.findById(detachedCourier.getAllotedToDeliveryBoy().getId()).get();
		detachedCourier.setAllotedToDeliveryBoy(employee);
		detachedCourier.setStatus(StatusEnum.valueOf("IN_TRANSIT"));
		return courierRepo.save(detachedCourier);
	}

	@Override
	public List<CourierEntity> getAllOrdersToBeDelivered(long empId, StatusEnum valueOf) {
		List<CourierEntity> courierList = courierRepo.findByAllotedToDeliveryBoyIdAndStatus(empId, StatusEnum.valueOf("DISPATCHED"));
		return courierList;
	}
	
	@Override
	public CourierEntity updateCourierStatusToDelivered(long courierId) {
		CourierEntity detachedCourier = courierRepo.findById(courierId).get();
		CustomerEntity customer = customerRepo.findById(detachedCourier.getCustomerCouriers().getId()).get();
		detachedCourier.setCustomerCouriers(customer);
		EmployeeEntity employee = employeeRepo.findById(detachedCourier.getAllotedToDeliveryBoy().getId()).get();
		detachedCourier.setAllotedToDeliveryBoy(employee);
		detachedCourier.setStatus(StatusEnum.valueOf("DELIVERED"));
		return courierRepo.save(detachedCourier);
	}

	@Override
	public CourierEntity updateCourierStatusToUnsuccessfulDelivery(long courierId) {
		CourierEntity detachedCourier = courierRepo.findById(courierId).get();
		CustomerEntity customer = customerRepo.findById(detachedCourier.getCustomerCouriers().getId()).get();
		detachedCourier.setCustomerCouriers(customer);
		EmployeeEntity employee = employeeRepo.findById(detachedCourier.getAllotedToDeliveryBoy().getId()).get();
		detachedCourier.setAllotedToDeliveryBoy(employee);
		detachedCourier.setStatus(StatusEnum.valueOf("UNSUCCESSFUL_DELIVERY"));
		return courierRepo.save(detachedCourier);
	}

}
