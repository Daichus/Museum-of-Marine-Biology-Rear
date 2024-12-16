package com.example.demo.repository;

import com.example.demo.model.MuseumProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MuseumProgramRepository extends JpaRepository<MuseumProgram, Integer> {
  List<MuseumProgram> findByDateAndStatusOrderByStartTime(LocalDate date, String status);
  List<MuseumProgram> findByWeekDayAndSessionAndStatusOrderByStartTime(Integer weekDay, String session, String status);
}