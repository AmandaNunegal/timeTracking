package com.nunegal.timeTracking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {

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
	
}
