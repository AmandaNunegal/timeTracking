package com.nunegal.timeTracking.dtos;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeTimekeepingDto {
	
	private Long idEmp;
	
	private boolean enabledEmp = true;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de empleado debe tener entre 3 y 20 caracteres")
	private String nameEmp;
	
	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
	private String surnameEmp;
	
	private Long idTk;

	private String typeTk;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTimeTk;		
}
