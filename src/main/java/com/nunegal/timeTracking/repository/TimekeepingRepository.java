package com.nunegal.timeTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nunegal.timeTracking.entity.Timekeeping;

@Repository
public interface TimekeepingRepository extends JpaRepository<Timekeeping, Long>{

}
