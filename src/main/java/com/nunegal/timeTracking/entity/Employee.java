package com.nunegal.timeTracking.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "department")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_employee")
	private Long id;

	private String name;
	private String surname;
	private String username;
	private String password;
	private boolean enabled;

	@ManyToOne
	@JoinColumn(name = "id_department", nullable = false)
	private Department department;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)	
	private List<EmployeeSchedule> employeesSchedule = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Timekeeping> timekeepings = new ArrayList<>();
	
}
