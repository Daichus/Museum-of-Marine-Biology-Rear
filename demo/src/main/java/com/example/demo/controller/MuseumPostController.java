package com.example.demo.controller;

import com.example.demo.model.MuseumPost;
import com.example.demo.service.MuseumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class MuseumPostController {

  private final MuseumPostService museumPostService;

  @Autowired
  public MuseumPostController(MuseumPostService museumPostService) {
    this.museumPostService = museumPostService;
  }

  // 取得分頁的文章資料
  @GetMapping
  public Page<MuseumPost> getPagedPosts(
          @RequestParam(value = "type", defaultValue = "NEWS") String type,
          Pageable pageable) {
    return museumPostService.getPosts(type, pageable);
  }

  // 取得所有指定類型的活動
  @GetMapping("/all")
  public List<MuseumPost> getAllPosts(
          @RequestParam(value = "type", defaultValue = "NEWS") String type) {
    return museumPostService.getAllActivePosts(type);
  }
}

