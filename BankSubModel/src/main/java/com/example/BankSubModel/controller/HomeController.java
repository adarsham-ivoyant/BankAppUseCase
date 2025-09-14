package com.example.BankSubModel.controller;

import com.example.BankSubModel.JMS.CustomMessage;
import com.example.BankSubModel.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    public Repo repo;

    @GetMapping("/GetAllDetails")
    public List<CustomMessage> getAllCustomer(){
        return repo.findAll();
    }
}
