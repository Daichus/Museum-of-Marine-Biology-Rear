package com.example.demo.service;

import com.example.demo.model.MarineSpecies;
import com.example.demo.repository.MarineSpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarineSpeciesService {

  @Autowired
  MarineSpeciesRepository marineSpeciesRepository;

  public List<MarineSpecies> getAllMarineSpecies() {
    return marineSpeciesRepository.findAll();
  }

  public String addMarineSpecies(MarineSpecies marineSpecies) {
    marineSpeciesRepository.save(marineSpecies);
    return String.format("新增海洋生物成功\nid: %s\nname: %s\nImageURL: %s\n類別: %s\n全描述: %s\n小說明: %s\nlocation_id: %s",
            marineSpecies.getSpecies_id(),
            marineSpecies.getName(),
            marineSpecies.getImage_url(),
            marineSpecies.getCategory(),
            marineSpecies.getFullDescription(),
            marineSpecies.getDescription(),
            marineSpecies.getLocation_id());
  }

  public String updateMarineSpecies(MarineSpecies marineSpecies) {
    if(marineSpecies != null) {
      marineSpeciesRepository.save(marineSpecies);
      return "修改海洋生物資訊完成";
    } else {
      return "更新海洋生物資訊失敗";
    }
  }

  public String deleteMarineSpecies(int id) {
    marineSpeciesRepository.deleteById(id);
    return "刪除海洋生物資訊成功";
  }


}
