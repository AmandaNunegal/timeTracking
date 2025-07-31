package com.nunegal.timeTracking.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
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
import com.nunegal.timeTracking.dtos.EmployeeMoreDto;
import com.nunegal.timeTracking.dtos.EmployeeTimekeepingDto;
import com.nunegal.timeTracking.dtos.TimekeepingDto;
import com.nunegal.timeTracking.dtos.TimekeepingFormDto;
import com.nunegal.timeTracking.entity.Department;
import com.nunegal.timeTracking.entity.Employee;
import com.nunegal.timeTracking.entity.EmployeeSchedule;
import com.nunegal.timeTracking.entity.EmployeeScheduleId;
import com.nunegal.timeTracking.entity.Schedule;
import com.nunegal.timeTracking.entity.Timekeeping;
import com.nunegal.timeTracking.enums.TypeTimekeeping;
import com.nunegal.timeTracking.mappers.EmployeeMapper;
import com.nunegal.timeTracking.mappers.TimekeepingMapper;
import com.nunegal.timeTracking.services.DepartmentService;
import com.nunegal.timeTracking.services.EmployeeScheduleService;
import com.nunegal.timeTracking.services.EmployeeService;
import com.nunegal.timeTracking.services.ScheduleService;
import com.nunegal.timeTracking.services.TimekeepingService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
public class TimeTrackingController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private EmployeeScheduleService employeeScheduleService;

	@Autowired
	private TimekeepingService timekeepingService;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private TimekeepingMapper timekeepingMapper;

	@GetMapping("/employees/list")
	public String findEmployees(Model model) {

		List<EmployeeDto> employeesDto = employeeMapper.toListEmployeeDto(employeeService.findAll());
		model.addAttribute("employees", employeesDto);
		model.addAttribute("empl", new EmployeeDto());

		return "employees-list";
	}

	@PostMapping("/employees/new")
	public String saveEmployee(@Valid @ModelAttribute("emp") EmployeeMoreDto empMoreDto, BindingResult bindingResult,
			Model model) {

		model.addAttribute("departments", departmentService.findAll());

		if (bindingResult.hasErrors()) {

			return "new-employee";

		}

		if (empMoreDto.getIdDept() == null) {

			bindingResult.rejectValue("idDept", "error.idDept", "Debe seleccionarse un departamento");
			return "new-employee";
		}

		if (empMoreDto.getTimeStart().isAfter(empMoreDto.getTimeEnd())) {

			bindingResult.rejectValue("timeEnd", "error.timeEnd",
					"La hora de salida debe ser posterior a la hora de entrada");
			return "new-employee";
		}

		if (empMoreDto.getTimeStart2() != null && empMoreDto.getTimeEnd2() == null) {

			bindingResult.rejectValue("timeEnd2", "error.timeEnd2", "La segunda hora de entrada está vacía");
			return "new-employee";
		}

		if (empMoreDto.getTimeStart2() == null && empMoreDto.getTimeEnd2() != null) {

			bindingResult.rejectValue("timeStart2", "error.timeStart2", "La segunda hora de salida está vacía");
			return "new-employee";
		}

		Employee employee = employeeMapper.toEmployee(empMoreDto);

		Department department = departmentService.findById(empMoreDto.getIdDept())
				.orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado"));
		employee.setDepartment(department);

		employeeService.save(employee);

		Schedule schedule = new Schedule();
		schedule.setTimeStart(empMoreDto.getTimeStart());
		schedule.setTimeEnd(empMoreDto.getTimeEnd());
		scheduleService.save(schedule);

		EmployeeSchedule empSchedule = new EmployeeSchedule();
		EmployeeScheduleId empScheduleId = new EmployeeScheduleId();

		empScheduleId.setIdEmployee(employee.getId());
		empScheduleId.setIdSchedule(schedule.getId());
		empScheduleId.setAssignmentDateStart(LocalDate.now());

		empSchedule.setId(empScheduleId);
		empSchedule.setEmployee(employee);
		empSchedule.setSchedule(schedule);

		employeeScheduleService.save(empSchedule);

		if (empMoreDto.getTimeStart2() != null && empMoreDto.getTimeEnd2() != null) {

			Schedule schedule2 = new Schedule();

			schedule2.setTimeStart(empMoreDto.getTimeStart2());
			schedule2.setTimeEnd(empMoreDto.getTimeEnd2());
			scheduleService.save(schedule2);

			EmployeeSchedule empSchedule2 = new EmployeeSchedule();
			EmployeeScheduleId empScheduleId2 = new EmployeeScheduleId();

			empScheduleId2.setIdEmployee(employee.getId());
			empScheduleId2.setIdSchedule(schedule2.getId());
			empScheduleId2.setAssignmentDateStart(LocalDate.now());

			empSchedule2.setId(empScheduleId2);
			empSchedule2.setEmployee(employee);
			empSchedule2.setSchedule(schedule2);

			employeeScheduleService.save(empSchedule2);
		}

		return "redirect:/employees/listEmpMore";

	}

	@GetMapping("/employees/new")
	public String newEmployee(Model model) {

		model.addAttribute("emp", new EmployeeMoreDto());
		model.addAttribute("departments", departmentService.findAll());
		return "new-employee";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/employees/{id}/edit")
	public String modalEditEmployee(@PathVariable Long id, Model model) {

		model.addAttribute("employees", employeeMapper.toListEmployeeDto(employeeService.findAll()));
		model.addAttribute("empl", employeeMapper.toEmployeeDto(employeeService.findById(id).get()));
		// model.addAttribute("modalOpened", true);

		return "employees-list";

	}

	@PostMapping("/employees/{id}/edit")
	public ResponseEntity<?> editEmployee(@PathVariable Long id, @Valid @ModelAttribute("empl") EmployeeDto employeeDto,
			BindingResult bindingResult, Model model) {

		Employee employeeExisting = employeeService.findByUsername(employeeDto.getUsername())
				.orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

		if (!employeeExisting.getId().equals(employeeDto.getId())) {
			bindingResult.rejectValue("username", "error.username", "El nombre de usuario ya está en uso");

		}

		if (bindingResult.hasErrors()) {

			Map<String, List<String>> errors = bindingResult.getFieldErrors().stream().collect(Collectors.groupingBy(
					FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

			return ResponseEntity.badRequest().body(errors);
		}

		employeeExisting.setName(employeeDto.getName());
		employeeExisting.setSurname(employeeDto.getSurname());
		employeeExisting.setUsername(employeeDto.getUsername());

		Employee updatedEmployee = employeeService.edit(employeeExisting);

		return ResponseEntity.ok(employeeMapper.toEmployeeDto(updatedEmployee));

	}

	@GetMapping("/employees/more/{id}/edit")
	public String modalEditEmployeeMore(@PathVariable Long id, Model model) {

		model.addAttribute("employees", employeeMapper.toListEmployeeDto(employeeService.findAll()));
		model.addAttribute("empl", employeeMapper.toEmployeeDto(employeeService.findById(id).get()));
		// model.addAttribute("modalOpened", true);

		return "employees-list";

	}

	// @PostMapping("/employees/more/{id}/edit")
	// public ResponseEntity<?> editEmployeeMore(@PathVariable Long id, @Valid
	// @ModelAttribute("empl") EmployeeDto employeeDto,
	// BindingResult bindingResult, Model model) {

	/*
	 * Employee employeeExisting =
	 * employeeService.findByUsername(employeeDto.getUsername()) .orElseThrow(() ->
	 * new EntityNotFoundException("Empleado no encontrado"));
	 * 
	 * if (!employeeExisting.getId().equals(employeeDto.getId())) {
	 * bindingResult.rejectValue("username", "error.username",
	 * "El nombre de usuario ya está en uso");
	 * 
	 * }
	 * 
	 * if (bindingResult.hasErrors()) {
	 * 
	 * Map<String, List<String>> errors =
	 * bindingResult.getFieldErrors().stream().collect(Collectors.groupingBy(
	 * FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage,
	 * Collectors.toList())));
	 * 
	 * return ResponseEntity.badRequest().body(errors); }
	 * 
	 * employeeExisting.setName(employeeDto.getName());
	 * employeeExisting.setSurname(employeeDto.getSurname());
	 * employeeExisting.setUsername(employeeDto.getUsername());
	 * 
	 * Employee updatedEmployee = employeeService.edit(employeeExisting);
	 * 
	 * return ResponseEntity.ok(employeeMapper.toEmployeeDto(updatedEmployee));
	 */

	// }

	@GetMapping("/employees/listEmpMore")
	public String findEmployeesMoreData(Model model) {

		List<EmployeeMoreDto> employeesMoreDto = employeeMapper.toListEmployeeMoreDto(employeeService.findEmployees());
		model.addAttribute("employees", employeesMoreDto);
		model.addAttribute("empl", new EmployeeMoreDto());

		List<Department> departments = departmentService.findAll();
		model.addAttribute("departments", departments);
		return "employees-more-list";
	}

	@GetMapping("/timekeeping/list")
	public String findAllTimekeeping(Model model) {

		List<EmployeeTimekeepingDto> timekeepings = employeeService.findAllTimekeepings();

		model.addAttribute("timekeepings", timekeepings);

		return "timekeeping-list";
	}

	@GetMapping("/timekeeping/signup")
	public String signupTimekeeping(Model model) {

	    TimekeepingFormDto timekeepingF = new TimekeepingFormDto();
	   
	    for (TypeTimekeeping type : TypeTimekeeping.values()) {
	        TimekeepingDto tkDto = new TimekeepingDto();
	        tkDto.setType(type); 
	        timekeepingF.getTimekeepings().add(tkDto);
	    }

	    model.addAttribute("timekeepingForm", timekeepingF);

	    return "timekeeping-signup";
	}

	@PostMapping("/timekeeping/signup")
	public String saveSignupTimekeeping(@ModelAttribute("timekeepingForm") @Valid TimekeepingFormDto timekeepingForm,
	                                    BindingResult result,
	                                    Model model,
	                                    Principal principal) {

	    if (result.hasErrors()) {
	        model.addAttribute("types", Arrays.asList(TypeTimekeeping.values()));
	        return "timekeeping-signup";
	    }

	    Optional<Employee> employeeOpt = employeeService.findByUsername(principal.getName());

	    if (employeeOpt.isEmpty()) {
	        return "redirect:/error";
	    }

	    Employee employee = employeeOpt.get();

	    List<Timekeeping> timekeepings = timekeepingForm.getTimekeepings().stream()
	        .filter(tkDto -> tkDto.getDateTime() != null)
	        .map(tkDto -> {
	            Timekeeping tk = timekeepingMapper.toTimekeeping(tkDto);
	            tk.setEmployee(employee);
	            return tk;
	        })
	        .collect(Collectors.toList());

	    timekeepingService.saveAll(timekeepings);

	    return "redirect:/";
	}

	
}
