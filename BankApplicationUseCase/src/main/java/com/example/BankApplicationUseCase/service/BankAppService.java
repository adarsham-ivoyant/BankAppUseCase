package com.example.BankApplicationUseCase.service;

import com.example.BankApplicationUseCase.entity.Customer;
import com.example.BankApplicationUseCase.entity.Transactions;
import jakarta.transaction.Transaction;

import java.util.List;

public interface BankAppService {

    public String customerRegistration(Customer customer);

    public String addMoneyToAccount(Long accuntNumber,Double amount);

    public String withdrawMoney(Long accuntNumber,Double amount);

    public List<Customer> getAllCustomer();


    public String moneyTransfer(Long fromAccNum,Long toAccNum, Double amount);

    public List<Transactions> getAllTransction();

    public void generateTransactionsCSV();

    public List<Transactions> searchTransactionByAccountNumber(Long accountNum);
}
