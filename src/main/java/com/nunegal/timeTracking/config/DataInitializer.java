package com.nunegal.timeTracking.config;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nunegal.timeTracking.entity.Department;
import com.nunegal.timeTracking.entity.Employee;
import com.nunegal.timeTracking.entity.EmployeeSchedule;
import com.nunegal.timeTracking.entity.EmployeeScheduleId;
import com.nunegal.timeTracking.entity.Schedule;
import com.nunegal.timeTracking.services.DepartmentService;
import com.nunegal.timeTracking.services.EmployeeScheduleService;
import com.nunegal.timeTracking.services.EmployeeService;
import com.nunegal.timeTracking.services.ScheduleService;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private EmployeeScheduleService employeeScheduleService;
	
	@Override
	public void run(String[] args) throws Exception {
		
	if (employeeService.count() > 0) return;
		
		Department department = new Department();
		department.setName("RRHH");
		department.setDescription("Recursos humanos");
		departmentService.save(department);
		
		Schedule schedule = new Schedule();
		schedule.setTimeStart(LocalTime.of(8, 0));
		schedule.setTimeEnd(LocalTime.of(16, 0));
		scheduleService.save(schedule);
				
		Employee employee = new Employee();
		employee.setUsername("usuario");
		employee.setPassword("1234");
		employee.setName("Julio");
		employee.setSurname("Ríos");	
		employee.setEnabled(true);
		employee.setDepartment(department);
		
		employeeService.save(employee);
		
		EmployeeScheduleId id = new EmployeeScheduleId();
		id.setIdEmployee(employee.getId());
		id.setIdSchedule(schedule.getId());
		id.setAssignmentDateStart(LocalDate.of(2020,01,01));
		
		EmployeeSchedule employeeSchedule = new EmployeeSchedule();
		employeeSchedule.setId(id);
		employeeSchedule.setEmployee(employee);		
		employeeSchedule.setSchedule(schedule);		
		employeeSchedule.setAssignmentDateEnd(LocalDate.now());
		
		employee.getEmployeesSchedule().add(employeeSchedule);		
		employeeScheduleService.save(employeeSchedule);
		
		
	}

}
