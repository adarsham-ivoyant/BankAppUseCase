package com.example.BankApplicationUseCase.repository;

import com.example.BankApplicationUseCase.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDB extends JpaRepository<Transactions,Long> {

    public Transactions findByAccNum(Long accNum);

}
