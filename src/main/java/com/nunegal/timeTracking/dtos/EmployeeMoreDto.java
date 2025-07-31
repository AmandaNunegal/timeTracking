package com.nunegal.timeTracking.dtos;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.nunegal.timeTracking.validation.PasswordMatches;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
public class EmployeeMoreDto {

	private Long id;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de empleado debe tener entre 3 y 20 caracteres")
	private String name;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
	private String surname;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de empleado debe tener entre 3 y 20 caracteres")
	private String username;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de empleado debe tener entre 3 y 20 caracteres")
	private String password;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de empleado debe tener entre 3 y 20 caracteres")
	private String password2;
	
	private boolean enabled = true;
	
	@NotNull(message = "Este campo no debe estar vacío")
	private Long idDept;
	
	//@NotBlank(message = "Este campo no debe estar vacío")
	//@Size(min = 3, max = 20, message = "El nombre de departamento debe tener entre 3 y 20 caracteres")
	private String nameDepartment;
		
	private Long idSched;
	
	@NotNull(message = "Se debe seleccionar una hora de entrada")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime timeStart;
	
	@NotNull(message = "Se debe seleccionar una hora de salida")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime timeEnd;
	
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime timeStart2;
	
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime timeEnd2;
	
	
	
	
	
}
