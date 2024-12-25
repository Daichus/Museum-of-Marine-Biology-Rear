package com.example.demo.service;


import com.example.demo.model.Admin;
import com.example.demo.model.FAQ;
import com.example.demo.model.MarineSpecies;


import com.example.demo.model.dto.LoginDto;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.FAQRepository;
import com.example.demo.repository.MarineSpeciesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    FAQRepository fr;

    @Autowired
    MarineSpeciesRepository msr;

    @Autowired
    AdminRepository adminRepository;


    public ResponseEntity<?>login(LoginDto dto){
        System.out.println(dto.getName());
        Admin admin = adminRepository.findByName(dto.getName());

        if(admin != null) {
            if(admin.getPassword().equals(dto.getPassword())) {
                return ResponseEntity.ok("登錄成功");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密碼錯誤");
            }
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到此用戶,請檢查名稱是否正確");
        }


    }



    /**
     * 以下為維護FAQ的方法
     */
    public void addFAQ(FAQ faq) {
        fr.save(faq);
    }

    public void updateFAQ(FAQ faq) {
        fr.save(faq);
    }

    public void deleteFAQById(int id) {
        fr.deleteById(id);
    }

    public List<FAQ> getAllFAQ() {
        return fr.findAll();
    }

    /**
     * 以下為維護海洋生物介紹的方法
     */
    public void addMarineSpecies (MarineSpecies species) {
        msr.save(species);
    }

    public void editMarineSpecies(MarineSpecies species) {
        msr.save(species);
    }

    public void deleteMarineSpeciesById(int id) {
        msr.deleteById(id);
    }

    public List<MarineSpecies> getAllSpecies() {
        return msr.findAll();
    }




}
