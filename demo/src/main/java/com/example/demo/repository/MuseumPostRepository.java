package com.example.demo.repository;

import com.example.demo.model.MuseumPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseumPostRepository extends JpaRepository<MuseumPost, Integer> {
  Page<MuseumPost> findByTypeAndStatusOrderByDateDesc(String type, String status, Pageable pageable);
  List<MuseumPost> findByTypeAndStatusOrderByDateDesc(String type, String status);
}
