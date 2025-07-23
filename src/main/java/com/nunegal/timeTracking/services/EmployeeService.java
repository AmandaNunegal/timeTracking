package com.nunegal.timeTracking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nunegal.timeTracking.entity.Employee;
import com.nunegal.timeTracking.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee employee = employeeRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado al usuario"));

		return new User(employee.getUsername(), employee.getPassword(), employee.isEnabled(), true, // Cuenta no
																									// expirada
				true, // Credenciales no expirados
				true, // Cuenta no bloqueada
				List.of() // sin roles
		);

	}

	public Employee saveEmployee(Employee employee) {

		String encodedPassword;

		if (employee.getName() == null || employee.getSurname() == null || employee.getUsername() == null
				|| employee.getPassword() == null) {
			throw new NullPointerException("El nombre, el apellido y la contraseña no pueden estar vacíos");
		}
		if (employee.getName().isBlank() || employee.getSurname().isBlank() || employee.getUsername().isBlank()
				|| employee.getPassword().isBlank()) {

			throw new IllegalArgumentException(
					"El nombre, apellido, nombre de usuario y contraseña no pueden estar vacíos");
		}

		if ((employee.getName().length() < 3 || employee.getName().length() > 20)
				|| (employee.getSurname().length() < 3 || employee.getSurname().length() > 20)
				|| (employee.getUsername().length() < 3 || employee.getUsername().length() > 20)
				|| (employee.getPassword().length() < 3 || employee.getPassword().length() > 20)) {
			throw new IllegalArgumentException(
					"El nombre, apellido, nombre de usuario y contraseña deben tener entre 3 y 20 caracteres");
		}

		encodedPassword = passwordEncoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		employee.setEnabled(true);
		return employeeRepository.save(employee);

	}

	public Employee edit(Employee employee) {

		Optional<Employee> employeeExisting = employeeRepository.findById(employee.getId());
		
		if (employeeExisting.isEmpty()) {
			throw new EntityNotFoundException("Empleado no encontrado");
		}
		
		Employee employeeCurrent = employeeExisting.get();
		
		employee.setPassword(employeeCurrent.getPassword());
		employee.setEnabled(employeeCurrent.isEnabled());
		
		if (employee.getName() == null || employee.getSurname() == null || employee.getUsername() == null) {
			throw new NullPointerException("El nombre y el apellido no pueden estar vacíos");
		}
		
		if (employee.getName().isBlank() || employee.getSurname().isBlank() || employee.getUsername().isBlank()) {

			throw new IllegalArgumentException(
					"El nombre, apellido y el nombre de usuario no pueden estar vacíos");
		}

		if ((employee.getName().length() < 3 || employee.getName().length() > 20)
				|| (employee.getSurname().length() < 3 || employee.getSurname().length() > 20)
				|| (employee.getUsername().length() < 3 || employee.getUsername().length() > 20)) {
			throw new IllegalArgumentException(
					"El nombre, apellido y nombre de usuario debe tener entre 3 y 20 caracteres");
		}
		
		return employeeRepository.save(employee);

	}

	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(Long id) {

		return employeeRepository.findById(id);

	}
	
	public Optional<Employee> findByUsername(String username) {
		
		return employeeRepository.findByUsername(username);
	}

}
