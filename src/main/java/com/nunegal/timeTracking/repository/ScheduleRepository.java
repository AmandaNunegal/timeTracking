package com.nunegal.timeTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nunegal.timeTracking.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
