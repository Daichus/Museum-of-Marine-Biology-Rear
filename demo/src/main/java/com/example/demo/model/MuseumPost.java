package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "MuseumPosts")
@Data
public class MuseumPost {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  @Lob
  private String summary;

  @Column(nullable = false)
  @Lob
  private String details;

  @Column(name = "location_id")
  private Integer locationId;

  @Column(nullable = false)
  private String status = "active";
}
