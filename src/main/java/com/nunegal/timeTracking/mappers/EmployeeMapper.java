package com.nunegal.timeTracking.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nunegal.timeTracking.dtos.EmployeeDto;
import com.nunegal.timeTracking.dtos.EmployeeDtoEdit;
import com.nunegal.timeTracking.entity.Employee;

@Mapper
public interface EmployeeMapper {

	
	EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
	
	
	EmployeeDto toEmployeeDto(Employee employee);
	
	EmployeeDtoEdit toEmployeeDtoEdit(Employee employee);
		
	Employee toEmployee(EmployeeDto employeeDto);
	
	Employee toEmployee(EmployeeDtoEdit employeeDtoEdit);
	
	List<EmployeeDto> toListEmployeeDto(List<Employee> employees);
	
	List<EmployeeDtoEdit> toListEmployeeDtoEdit(List<Employee> employees);	
	
	List<Employee> toListEmployee(List<EmployeeDto> employeesDto);
}
