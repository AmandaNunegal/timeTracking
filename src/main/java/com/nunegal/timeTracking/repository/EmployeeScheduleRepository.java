package com.nunegal.timeTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nunegal.timeTracking.entity.EmployeeSchedule;
import com.nunegal.timeTracking.entity.EmployeeScheduleId;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, EmployeeScheduleId>{

}
