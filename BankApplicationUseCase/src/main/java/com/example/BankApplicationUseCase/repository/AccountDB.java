package com.example.BankApplicationUseCase.repository;

import com.example.BankApplicationUseCase.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDB extends JpaRepository<Account,Long> {
}
