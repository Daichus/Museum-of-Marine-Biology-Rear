package com.example.demo.repository;

import com.example.demo.model.MarineSpecies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarineSpeciesRepository extends JpaRepository<MarineSpecies,Integer> {
}
