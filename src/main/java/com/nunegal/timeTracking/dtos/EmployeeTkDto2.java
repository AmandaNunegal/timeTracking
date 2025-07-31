package com.nunegal.timeTracking.dtos;

import java.util.List;

import com.nunegal.timeTracking.entity.Timekeeping;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeTkDto2 {
	
	private Long id;
	
	private boolean enabled = true;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de empleado debe tener entre 3 y 20 caracteres")
	private String name;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
	private String surname;
	
	private List<Timekeeping> timekeepings;	
}
