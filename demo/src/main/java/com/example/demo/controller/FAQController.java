package com.example.demo.controller;

import com.example.demo.model.FAQ;
import com.example.demo.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FAQ")
public class FAQController {

  @Autowired
  FAQService faqService;

  @GetMapping("/get")
  public List<FAQ> getAllFAQ() {
    return faqService.getAllFAQ();
  }

  @PostMapping("/add")
  public String addFAQ(@RequestBody FAQ faq) {
    return faqService.addFAQ(faq);
  }

  @PutMapping("/edit")
  public String updateFAQ(@RequestBody FAQ faq) {
    return faqService.updateFAQ(faq);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteFAQ(@PathVariable int id) {
    return faqService.deleteFAQ(id);
  }

}
