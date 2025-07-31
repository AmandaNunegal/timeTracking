package com.nunegal.timeTracking.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = "employees")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_department")
	private Long id;
	
	private String name;
	private String description;

	@OneToMany(mappedBy = "department", cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Employee> employees = new ArrayList<>();
	
	
}
