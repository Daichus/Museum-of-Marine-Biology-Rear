package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name="EventSchedule")
public class EventSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="schedule_id")
    private int schedule_id;

    @Column(name="start_time")
    private LocalDateTime start_time;

    @Column(name="end_time")
    private LocalDateTime end_time;





}
