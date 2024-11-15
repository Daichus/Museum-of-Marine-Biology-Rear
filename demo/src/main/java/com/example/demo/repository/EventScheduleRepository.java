package com.example.demo.repository;

import com.example.demo.model.EventSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventScheduleRepository extends JpaRepository<EventSchedule,Integer> {

}
