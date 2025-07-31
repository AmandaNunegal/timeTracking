package com.nunegal.timeTracking.dtos;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.nunegal.timeTracking.enums.TypeTimekeeping;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TimekeepingDto {
	
	private Long id;

	private TypeTimekeeping type;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;	
	
	
}
