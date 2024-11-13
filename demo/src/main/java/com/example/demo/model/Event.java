package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="Event")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private int event_id;

    @Column(name="name")
    private String name;

    @Column(name="location_id")
    private int location_id;

    @Column(name="schedule_id")
    private int schedule_id;

}
