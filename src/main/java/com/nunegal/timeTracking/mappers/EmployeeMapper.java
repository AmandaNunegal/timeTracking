package com.nunegal.timeTracking.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nunegal.timeTracking.dtos.EmployeeDto;
import com.nunegal.timeTracking.dtos.EmployeeMoreDto;
import com.nunegal.timeTracking.dtos.EmployeeTimekeepingDto;
import com.nunegal.timeTracking.entity.Employee;
import com.nunegal.timeTracking.entity.Timekeeping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeDto toEmployeeDto(Employee employee);

	Employee toEmployee(EmployeeDto employeeDto);

	@Mapping(source = "department.id", target = "idDept")
	@Mapping(source = "department.name", target = "nameDepartment")
	EmployeeMoreDto toEmployeeMoreDto(Employee employee);

	@Mapping(target = "department", ignore = true)
	Employee toEmployee(EmployeeMoreDto employeeMoreEditDto);
	
	@Mapping(source = "employee.id", target = "idEmp")
	@Mapping(source = "employee.enabled", target = "enabledEmp")
	@Mapping(source = "employee.name", target = "nameEmp")
	@Mapping(source = "employee.surname", target = "surnameEmp")	
	@Mapping(source = "timekeeping.id", target = "idTk")
	@Mapping(source = "timekeeping.type", target = "typeTk")
	@Mapping(source = "timekeeping.dateTime", target = "dateTimeTk")	
	EmployeeTimekeepingDto toEmployeeTkDto(Employee employee, Timekeeping timekeeping);
		
	Employee employee(EmployeeTimekeepingDto employeeTimekeepingDto);

	List<EmployeeDto> toListEmployeeDto(List<Employee> employees);

	List<Employee> toListEmployee(List<EmployeeDto> employeesDto);

	List<EmployeeMoreDto> toListEmployeeMoreDto(List<Employee> employees);
	

}
