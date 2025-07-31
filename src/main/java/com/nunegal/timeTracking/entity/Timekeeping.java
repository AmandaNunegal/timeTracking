package com.nunegal.timeTracking.entity;

import java.time.LocalDateTime;

import com.nunegal.timeTracking.enums.TypeTimekeeping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Timekeeping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_timekeeping")
	private Long id;	
	
    @Column(name="type")
    private TypeTimekeeping type;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", nullable = false)
	private Employee employee;
	
	
}
