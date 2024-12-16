package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "MuseumPrograms")
@Data
public class MuseumProgram {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String programCode;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  @Lob
  private String description;

  private String imgUrl;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private LocalTime startTime;

  @Column(nullable = false)
  private LocalTime endTime;

  @Column(nullable = false)
  private String duration;

  @Column(name = "location_id")
  private Integer locationId;

  @Column(nullable = false)
  private Integer weekDay;

  @Column(nullable = false)
  private String session;

  private String status = "active";
}