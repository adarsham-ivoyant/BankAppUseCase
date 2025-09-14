package com.example.BankApplicationUseCase.service;

import com.example.BankApplicationUseCase.entity.Customer;
import com.example.BankApplicationUseCase.entity.Transactions;

import java.util.List;

public interface ServiceI {

    public String customerReg(Customer customer);

    public List<Customer> getAllCustomer();


    public String moneyTransfer(Long fromAccNum,Long toAccNum, Double amount);

    public Transactions getTransctionById(Long accNum);
}
