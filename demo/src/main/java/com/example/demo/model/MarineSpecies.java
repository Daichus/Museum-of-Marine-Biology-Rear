package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="MarineSpecies")
public class MarineSpecies {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="species_id")
    private int species_id;

    @Column(name="image_url")
    private String image_url;

    @Column(name="habitat")
    private String habitat;

    @Column(name="description")
    private String description;

    @Column(name="location_id")
    private int location_id;
}
