package com.nunegal.timeTracking.dtos;

import java.time.LocalTime;

import lombok.Data;

@Data
public class ScheduleEmpDto {

	private Long id;
	
	private LocalTime timeStart;

	private LocalTime timeEnd;
	
	private LocalTime timeStart2;
	
	private LocalTime timeEnd2;
	
}
