package com.nunegal.timeTracking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nunegal.timeTracking.dtos.TimekeepingDto;
import com.nunegal.timeTracking.entity.Timekeeping;
import com.nunegal.timeTracking.mappers.TimekeepingMapper;
import com.nunegal.timeTracking.repository.TimekeepingRepository;

@Service
public class TimekeepingService {

	@Autowired
	private TimekeepingRepository timekeepingRepository;

	public Optional<Timekeeping> findById(Long id) {

		return timekeepingRepository.findById(id);

	}

	public List<Timekeeping> findAll() {

		return timekeepingRepository.findAll();
	}

	public Timekeeping save(Timekeeping timekeeping) {

		return timekeepingRepository.save(timekeeping);
	}
	
	public List<Timekeeping> saveAll(List<Timekeeping> timekeepings) {
		
		return timekeepingRepository.saveAll(timekeepings);
	}
	
	
}
