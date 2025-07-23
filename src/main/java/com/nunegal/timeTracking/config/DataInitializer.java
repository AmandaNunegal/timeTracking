package com.nunegal.timeTracking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nunegal.timeTracking.services.EmployeeService;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public void run(String[] args) throws Exception {
		
		/* String username = "usuario";
		String password = "1234";
		String name = "Julio";
		String surname = "Ríos";

		//employeeService.saveEmployee(name, surname, username, password); */
	}

}
