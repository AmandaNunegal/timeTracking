package com.nunegal.timeTracking.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeScheduleId implements Serializable{

	@Column(name = "id_employee")
	private Long idEmployee;
	
	@Column(name = "id_schedule")
	private Long idSchedule;
	
	@Column(name = "assignment_date_start")
	private LocalDate assignmentDateStart;
	
}
