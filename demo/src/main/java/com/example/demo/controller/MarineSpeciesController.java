package com.example.demo.controller;

import com.example.demo.model.MarineSpecies;
import com.example.demo.service.MarineSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MarineSpecies")
public class MarineSpeciesController {

  @Autowired
  MarineSpeciesService marineSpeciesService;

  @GetMapping("/get")
  public List<MarineSpecies> getAllMarineSpecies() {
    return marineSpeciesService.getAllMarineSpecies();
  }

  @PostMapping("/add")
  public String addMarineSpecies(@RequestBody MarineSpecies marineSpecies) {
    return marineSpeciesService.addMarineSpecies(marineSpecies);
  }

  @PutMapping("/edit")
  public String updateMarineSpecies(@RequestBody MarineSpecies marineSpecies) {
    return marineSpeciesService.updateMarineSpecies(marineSpecies);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteMarineSpecies(@PathVariable int id){
    return marineSpeciesService.deleteMarineSpecies(id);
  }


}
