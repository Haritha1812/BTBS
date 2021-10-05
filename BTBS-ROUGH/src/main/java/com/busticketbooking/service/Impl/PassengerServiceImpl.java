package com.busticketbooking.service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.busticketbooking.exception.BusinessLogicException;
import com.busticketbooking.exception.DatabaseException;
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
	private static final Logger logger = LogManager.getLogger(PassengerServiceImpl.class.getName());

	@Override
	public String addPassenger(PassengerDto dto) {

		logger.info("Entering Add Passenger in service layer");
		try {
			Passenger passenger = PassengerMapper.dtoToEntity(dto);
			Customer customer = customerDao.getCustomerById(passenger.getCustomer().getId());
			Bus bus = busDao.getBusById(passenger.getBus().getId());
			passenger.setBus(bus);
			passenger.setCustomer(customer);

			return passengerDao.addPassenger(passenger);

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String deletePassenger(Long id) {
		logger.info("Entering Delete Passenger in service layer");

		try {
			if (passengerDao.isPassengerExists(id))
				return passengerDao.deletePassenger(id);
			throw new BusinessLogicException("Passenger with Passenger Id:" + id + " Not Found!");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public boolean isPassengerExists(Long id) {

		try {
			if (passengerDao.isPassengerExists(id))
				return passengerDao.isPassengerExists(id);
			else
				throw new BusinessLogicException("Passenger with passenger Id : " + id + " Not found");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Passenger> getAllPassenger() {
		logger.info("Entering Get Passengers in service layer");

		try {
			if ((passengerDao.getAllPassenger()).size() != 0)
				return passengerDao.getAllPassenger();
			throw new BusinessLogicException("No Passenger Data available");
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Passenger> getPassengerByBusIdAndCusId(Long busid, Long cusid) {
		logger.info("Entering Get Passenger by customer and bus id in service layer");

		try {
			Bus bus = busDao.getBusById(busid);
			Customer customer = customerDao.getCustomerById(cusid);
			return passengerDao.getPassengerByBusIdAndCusId(bus, customer);
		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<Passenger> getPassengerByCusId(Long cusid) {
		logger.info("Entering Get Passenger by customer id in service layer");

		try {
			Customer customer = customerDao.getCustomerById(cusid);
			if (customer != null)
				return passengerDao.getPassengerByCusId(customer);
			throw new BusinessLogicException("No Cutomer Data available");

		} catch (DatabaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}
}
