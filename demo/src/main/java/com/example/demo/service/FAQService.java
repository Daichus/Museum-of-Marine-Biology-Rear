package com.example.demo.service;

import com.example.demo.model.FAQ;
import com.example.demo.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQService {
  @Autowired
  FAQRepository faqRepository;

  public List<FAQ> getAllFAQ() {
    return faqRepository.findAll();
  }

  public String addFAQ(FAQ faq) {
    faqRepository.save(faq);
    return String.format("新增 Q&A 成功\nQ&A_id: %s\nQ: %s\nAns: %s\n類別: %s",
            faq.getFaq_id(),
            faq.getQuestion(),
            faq.getAnswer(),
            faq.getCategory());
  }

  public String updateFAQ(FAQ faq) {
    if(faq != null) {
      faqRepository.save(faq);
      return "修改 Q&A 資訊完成";
    } else {
      return "更新 Q&A 資訊失敗";
    }
  }

  public String deleteFAQ(int id) {
    faqRepository.deleteById(id);
    return "刪除 Q&A 成功";
  }

}
