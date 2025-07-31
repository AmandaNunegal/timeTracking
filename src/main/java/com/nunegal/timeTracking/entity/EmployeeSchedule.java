package com.nunegal.timeTracking.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employee", "schedule"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "employee_schedule")
public class EmployeeSchedule {

	@EqualsAndHashCode.Include
	@EmbeddedId	
	private EmployeeScheduleId id;
		
	@ManyToOne
	@MapsId("idEmployee")
	@JoinColumn(name = "id_employee", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@MapsId("idSchedule")
	@JoinColumn(name = "id_schedule", nullable = false)
	private Schedule schedule;
	
	@Column(name="assignment_date_end")
	private LocalDate assignmentDateEnd;
	
	
}
