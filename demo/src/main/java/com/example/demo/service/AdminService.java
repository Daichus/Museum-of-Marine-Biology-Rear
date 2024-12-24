package com.example.demo.service;


import com.example.demo.model.FAQ;
import com.example.demo.model.MarineSpecies;


import com.example.demo.repository.FAQRepository;
import com.example.demo.repository.MarineSpeciesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    FAQRepository fr;

    @Autowired
    MarineSpeciesRepository msr;





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
