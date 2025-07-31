package com.nunegal.timeTracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nunegal.timeTracking.entity.Department;
import com.nunegal.timeTracking.entity.Employee;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByName(String name);	
	
}
