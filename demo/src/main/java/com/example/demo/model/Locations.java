package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Locations")
public class Locations {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id")
    private int location_id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "marineSpeciesLocations")
    private Set<MarineSpecies> marineSpecies;

    @AllArgsConstructor
    @Data
    public static class Tickets {

        private String type;

        private int quantity;

        private int price;


    }
}
