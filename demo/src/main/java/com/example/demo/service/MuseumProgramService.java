package com.example.demo.service;

import com.example.demo.model.MuseumProgram;
import com.example.demo.repository.MuseumProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MuseumProgramService {
  private final MuseumProgramRepository museumProgramRepository;

  @Autowired
  public MuseumProgramService(MuseumProgramRepository museumProgramRepository) {
    this.museumProgramRepository = museumProgramRepository;
  }

  public List<MuseumProgram> getDailyPrograms(LocalDate date) {
    return museumProgramRepository.findByDateAndStatusOrderByStartTime(date, "active");
  }

  public List<MuseumProgram> getProgramsByWeekDayAndSession(Integer weekDay, String session) {
    return museumProgramRepository.findByWeekDayAndSessionAndStatusOrderByStartTime(weekDay, session, "active");
  }
}
