package com.example.demo.service;

import com.example.demo.model.MuseumPost;
import com.example.demo.repository.MuseumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuseumPostService {
  private final MuseumPostRepository museumPostRepository;

  @Autowired
  public MuseumPostService(MuseumPostRepository museumPostRepository) {
    this.museumPostRepository = museumPostRepository;
  }

  public Page<MuseumPost> getPosts(String type, Pageable pageable) {
    return museumPostRepository.findByTypeAndStatusOrderByDateDesc(type, "active", pageable);
  }

  public List<MuseumPost> getAllActivePosts(String type) {
    return museumPostRepository.findByTypeAndStatusOrderByDateDesc(type, "active");
  }
}
