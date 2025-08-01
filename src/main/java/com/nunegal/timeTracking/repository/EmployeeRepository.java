package com.nunegal.timeTracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nunegal.timeTracking.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByUsername(String username);
	
	//List<EmployeeDeptDto> findEmployeesWithDepartment();
	
}
