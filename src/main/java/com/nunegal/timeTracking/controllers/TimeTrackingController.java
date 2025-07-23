package com.nunegal.timeTracking.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nunegal.timeTracking.dtos.EmployeeDto;
import com.nunegal.timeTracking.dtos.EmployeeDtoEdit;
import com.nunegal.timeTracking.entity.Employee;
import com.nunegal.timeTracking.mappers.EmployeeMapper;
import com.nunegal.timeTracking.services.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class TimeTrackingController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees/list")
	public String findAllEmployees(Model model) {

		List<EmployeeDto> employeesDto = EmployeeMapper.mapper.toListEmployeeDto(employeeService.findAll());
		model.addAttribute("employees", employeesDto);
		model.addAttribute("empl", new EmployeeDto());

		return "employeeslist";
	}

	@PostMapping("/employees/new")
	public String saveEmployee(@Valid @ModelAttribute("emp") EmployeeDto empDto, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {

			return "newEmployee";

		}
		Employee employee = EmployeeMapper.mapper.toEmployee(empDto);

		employeeService.saveEmployee(employee);

		List<EmployeeDto> employeesDto = EmployeeMapper.mapper.toListEmployeeDto(employeeService.findAll());
		model.addAttribute("employees", employeesDto);

		return "redirect:/employees/list";

	}

	@GetMapping("/employees/new")
	public String newEmployee(Model model) {

		model.addAttribute("emp", new EmployeeDto());
		return "newEmployee";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/employees/{id}/edit")
	public String modalEditEmployee(@PathVariable Long id, Model model) {

		model.addAttribute("employees", EmployeeMapper.mapper.toListEmployeeDto(employeeService.findAll()));
		model.addAttribute("empl", EmployeeMapper.mapper.toEmployeeDto(employeeService.findById(id).get()));

		model.addAttribute("modalOpened", true);

		return "employeeslist";

	}

	@PostMapping("/employees/{id}/edit")
	public ResponseEntity<?> editEmployee(@PathVariable Long id,
			@Valid @ModelAttribute("empl") EmployeeDtoEdit employeeDtoEdit, BindingResult bindingResult, Model model) {

		Optional<Employee> employeeExisting = employeeService.findByUsername(employeeDtoEdit.getUsername());

		if (employeeExisting.isPresent() && !employeeExisting.get().getId().equals(employeeDtoEdit.getId())) {

			bindingResult.rejectValue("username", "error.username", "El nombre de usuario ya está en uso");

		}

		if (bindingResult.hasErrors()) {

			Map<String, List<String>> errors = bindingResult.getFieldErrors().stream()
			        .collect(Collectors.groupingBy(
			            FieldError::getField,
			            Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
			        ));
			
			return ResponseEntity.badRequest().body(errors);
		}

		Employee employeeEdited = EmployeeMapper.mapper.toEmployee(employeeDtoEdit);
		employeeService.edit(employeeEdited);

		return ResponseEntity.ok(EmployeeMapper.mapper.toEmployeeDto(employeeEdited));
	}

}
