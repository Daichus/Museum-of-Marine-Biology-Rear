package com.example.demo.service;


import com.example.demo.model.FAQ;
import com.example.demo.model.MarineSpecies;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.FAQRepository;
import com.example.demo.repository.MarineSpeciesRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    FAQRepository fr;

    @Autowired
    MarineSpeciesRepository msr;

    @Autowired
    UserRepository ur;

    @Autowired
    TicketRepository tr;

    /**
     *
     * 以下為維護使用者資料的方法
     *
     */
    public void addUser(User user) {
        ur.save(user);
    }

    public void editUser(User user) {
        ur.save(user);
    }

    public void deleteUser(int id) {
        ur.deleteById(id);
    }

    public List<User> getAllUser() {
        return ur.findAll();
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

    /**
     * 以下為維護票種的方法
     * 未包含新增與刪除選項
     */
    public void editTicket(Ticket ticket) {
        tr.save(ticket);
    }

    public List<Ticket> getAllTicket() {
        return tr.findAll();
    }


}
