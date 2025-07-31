package com.nunegal.timeTracking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.nunegal.timeTracking.entity.Department;
import com.nunegal.timeTracking.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Optional<Department> findById(Long id) {

		return departmentRepository.findById(id);

	}

	public List<Department> findAll() {

		return departmentRepository.findAll();
	}

	// Corregir
	public Department save(Department department) {

		return departmentRepository.save(department);
	}
	
	

}
