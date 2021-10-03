package com.busticketbooking.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busticketbooking.dao.BusDao;
import com.busticketbooking.dao.CustomerDao;
import com.busticketbooking.dao.PassengerDao;
import com.busticketbooking.dto.PassengerDto;
import com.busticketbooking.entity.Bus;
import com.busticketbooking.entity.Customer;
import com.busticketbooking.entity.Passenger;
import com.busticketbooking.entity.Route;
import com.busticketbooking.exception.IdNotFoundException;
import com.busticketbooking.service.PassengerService;
import com.busticketbooking.util.mapper.BusMapper;
import com.busticketbooking.util.mapper.PassengerMapper;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	PassengerDao passengerDao;

	@Autowired
	CustomerDao customerDao;
	@Autowired
	BusDao busDao;
	

	/*
	 * 
	 * @Override public boolean isPassengerExists(Long id) { // TODO Auto-generated
	 * method stub return passengerDao.isPassengerExists(id); }
	 * 
	 * @Override public List<Passenger> getAllPassenger() { // TODO Auto-generated
	 * method stub return passengerDao.getAllPassenger(); }
	 * 
	 * @Override public String deletePassenger(Long id) { // TODO Auto-generated
	 * method stub return passengerDao.deletePassenger(id); }
	 * 
	 * @Override public String addPassenger(PassengerDto dto) {
	 * 
	 * Passenger passenger = PassengerMapper.dtoToEntity(dto); return
	 * passengerDao.addPassenger(passenger);
	 * 
	 * 
	 * }
	 * 
	 * @Override public String updatePassenger(PassengerDto dto) {
	 * 
	 * Passenger passenger = PassengerMapper.dtoToEntity(dto); return
	 * passengerDao.updatePassenger(passenger); }
	 * 
	 * @Override public Passenger isPassengerEmailExists(String email) { // TODO
	 * Auto-generated method stub return passengerDao.isPassengerEmailExists(email);
	 * }
	 * 
	 * @Override public Passenger getPassengerByMobileNumber(String mobileNumber) {
	 * // TODO Auto-generated method stub return
	 * passengerDao.getPassengerByMobileNumber(mobileNumber); }
	 */

	@Override public String addPassenger(PassengerDto dto) {
	      
		 Passenger passenger = PassengerMapper.dtoToEntity(dto); 
		 Customer customer = customerDao.getCustomerById(passenger.getCustomer().getId());
		 passenger.setCustomer(customer);
         return passengerDao.addPassenger(passenger);

}


	@Override
	public String deletePassenger(Long id) {
		if(passengerDao.isPassengerExists(id))
		return passengerDao.deletePassenger(id); 
		throw new IdNotFoundException("Passenger with Passenger Id:"+id+" Not Found!");
	}


	@Override
	public boolean isPassengerExists(Long id) {
		 return passengerDao.isPassengerExists(id); 
	}


	@Override
	public List<Passenger> getAllPassenger() {
		if((passengerDao.getAllPassenger()).size()!=0)
		return passengerDao.getAllPassenger();
		throw new NullPointerException("No Passenger Data available");
	}


	@Override
	public List<Passenger> getPassengerByBusIdAndCusId(Long busid, Long cusid) {
	        Bus bus = busDao.getBusById(busid);
	        Customer customer = customerDao.getCustomerById(cusid);
	        return passengerDao.getPassengerByBusIdAndCusId(bus, customer);
	}


	@Override
	public List<Passenger> getPassengerByCusId(Long cusid) {

		Customer customer = customerDao.getCustomerById(cusid);
		return passengerDao.getPassengerByCusId(customer);
	}
}
