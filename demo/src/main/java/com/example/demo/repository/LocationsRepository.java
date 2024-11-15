package com.example.demo.repository;

import com.example.demo.model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Locations,Integer> {
}
