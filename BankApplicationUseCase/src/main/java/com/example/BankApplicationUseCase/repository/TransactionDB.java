package com.example.BankApplicationUseCase.repository;

import com.example.BankApplicationUseCase.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDB extends JpaRepository<Transactions,Long> {

    List<Transactions> findByFromAccNumOrToAccNum(Long fromAccNum, Long toAccNum);

}
