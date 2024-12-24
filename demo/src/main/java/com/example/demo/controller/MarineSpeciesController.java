package com.example.demo.controller;

import com.example.demo.model.MarineSpecies;
import com.example.demo.service.MarineSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/MarineSpecies")
public class MarineSpeciesController {

  @Autowired
  MarineSpeciesService marineSpeciesService;

  /**
   *獲取所有圖片
   */
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

  @Value("${upload.path}")
  private String uploadDir;

  @PostMapping("/upload/{id}")
  public ResponseEntity<?> uploadImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
    try {
      System.out.println("收到檔案：" + file.getOriginalFilename());
      System.out.println("檔案大小：" + file.getSize());
      System.out.println("內容類型：" + file.getContentType());

      if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("請選擇一張圖片上傳");
      }

      // 檢查檔案類型
      String contentType = file.getContentType();
      if(contentType == null || !contentType.startsWith("image/")) {
        return ResponseEntity.badRequest().body("只能上傳圖片檔案");
      }

      String imageUrl = marineSpeciesService.uploadImage(id, file);
      System.out.println("檔案已儲存至: " + new File(uploadDir).getAbsolutePath());
      return ResponseEntity.ok().body("圖片上傳成功，URL: " + imageUrl);
    } catch (IOException e) {
      e.printStackTrace(); // 新增這行來除錯
      return ResponseEntity.internalServerError().body("圖片上傳失敗: " + e.getMessage());
    } catch (RuntimeException e) {
      e.printStackTrace(); // 新增這行來除錯
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


}
