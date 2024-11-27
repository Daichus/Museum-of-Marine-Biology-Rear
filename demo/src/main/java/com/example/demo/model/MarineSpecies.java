package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MarineSpecies")
public class MarineSpecies {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="species_id")
    private int species_id;

    @Column(name="name")
    private String name;

    @Column(name="image_url")
    private String image_url;

    @Column(name="category")
    private String category;

    @Column(name="FullDescription")
    private String fullDescription;

    @Column(name="description")
    private String description;

    @Column(name="location_id")
    private int location_id;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Locations marineSpeciesLocations;
}
