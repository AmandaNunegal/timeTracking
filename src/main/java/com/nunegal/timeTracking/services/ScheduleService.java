package com.nunegal.timeTracking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nunegal.timeTracking.entity.Department;
import com.nunegal.timeTracking.entity.Schedule;
import com.nunegal.timeTracking.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	public Optional<Schedule> findById(Long id) {

		return scheduleRepository.findById(id);

	}

	public List<Schedule> findAll() {

		return scheduleRepository.findAll();
	}

	// Corregir
	public Schedule save(Schedule schedule) {

		return scheduleRepository.save(schedule);
	}

}
