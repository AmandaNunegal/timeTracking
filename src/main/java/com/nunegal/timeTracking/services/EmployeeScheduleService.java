package com.nunegal.timeTracking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nunegal.timeTracking.entity.EmployeeSchedule;
import com.nunegal.timeTracking.entity.EmployeeScheduleId;
import com.nunegal.timeTracking.repository.EmployeeScheduleRepository;

@Service
public class EmployeeScheduleService {

	@Autowired
	private EmployeeScheduleRepository employeeScheduleRepository;

	public Optional<EmployeeSchedule> findById(EmployeeScheduleId id) {

		return employeeScheduleRepository.findById(id);

	}

	public List<EmployeeSchedule> findAll() {

		return employeeScheduleRepository.findAll();
	}

	// Corregir
	public EmployeeSchedule save(EmployeeSchedule employeeSchedule) {

		return employeeScheduleRepository.save(employeeSchedule);
	}

}
