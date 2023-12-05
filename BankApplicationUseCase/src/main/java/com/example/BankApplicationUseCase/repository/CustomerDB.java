package com.example.BankApplicationUseCase.repository;

import com.example.BankApplicationUseCase.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDB extends JpaRepository<Customer,String> {
}
