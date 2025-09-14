package com.example.BankSubModel.repository;

import com.example.BankSubModel.JMS.CustomMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<CustomMessage,String> {
}
