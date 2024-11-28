package com.example.demo.service;

import com.example.demo.model.MarineSpecies;
import com.example.demo.repository.MarineSpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class MarineSpeciesService {

  @Autowired
  MarineSpeciesRepository marineSpeciesRepository;

  @Value("${upload.path}")
  private String uploadDir;

  public List<MarineSpecies> getAllMarineSpecies() {
    return marineSpeciesRepository.findAll();
  }

  public Optional<MarineSpecies> getMarineSpeciesById(int id) {
    return marineSpeciesRepository.findById(id);
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

  public String uploadImage(int id, MultipartFile file) throws IOException {
    System.out.println("開始上傳圖片");

    // 檢查海洋生物是否存在
    Optional<MarineSpecies> marineSpeciesOptional = marineSpeciesRepository.findById(id);
    if( !marineSpeciesOptional.isPresent() ) {
      throw new RuntimeException("找不到指定ID的海洋生物");
    }

    // 確保上傳目錄存在
    Path uploadPath = Paths.get(uploadDir);
    System.out.println("上傳路徑: " + uploadPath.toAbsolutePath());

    if( !Files.exists(uploadPath) ) {
      System.out.println("建立目錄: " + uploadPath);
      Files.createDirectories(uploadPath);
    }

    //生成唯一的檔案名稱
    String originalFilename = file.getOriginalFilename();
    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
    String newFileName = "marine_" + id + "_" + System.currentTimeMillis() + fileExtension;
    Path filePath = uploadPath.resolve(newFileName);
    System.out.println("完整檔案路徑: " + filePath.toAbsolutePath());

    //保存檔案
    Files.copy(file.getInputStream(), filePath);
    System.out.println("檔案儲存成功");

    //更新資料庫中的圖片URL
    MarineSpecies marineSpecies = marineSpeciesOptional.get();
    String imageUrl = "/uploads/" + newFileName;
    marineSpecies.setImage_url(imageUrl);
    marineSpeciesRepository.save(marineSpecies);

    return imageUrl;
  }


}
