package com.example.BankApplicationUseCase.service;

import com.example.BankApplicationUseCase.entity.Account;
import com.example.BankApplicationUseCase.entity.Customer;

import com.example.BankApplicationUseCase.entity.Transactions;
import com.example.BankApplicationUseCase.exception.AccountNotFoundException;
import com.example.BankApplicationUseCase.exception.InsufficentBalance;
import com.example.BankApplicationUseCase.repository.AccountDB;
import com.example.BankApplicationUseCase.repository.CustomerDB;

import com.example.BankApplicationUseCase.repository.TransactionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceImpl implements ServiceI {

    @Autowired
    public CustomerDB customerDB;

    @Autowired
    public AccountDB accountDB;

    @Autowired
    public TransactionDB transactionDB;

    @Override
    public String customerReg(Customer customer) {
        String customerId=UUID.randomUUID().toString();
          customer.setCustId(customerId);
          Customer savedCustomer = customerDB.save(customer);
          return savedCustomer.getCustId();
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerDB.findAll();
    }

    @Override
    public String moneyTransfer(Long fromAccNum, Long toAccNum, Double amount) {
        Account from=accountDB.findById(fromAccNum).orElseThrow(AccountNotFoundException::new);
        Account to=accountDB.findById(toAccNum).orElseThrow(AccountNotFoundException::new);

        if(from.getBalance().compareTo(amount)<0){
            throw new InsufficentBalance();
        }

        Double fromAmt=from.getBalance() - amount;
        Double toAmt=to.getBalance() + amount;

        from.setBalance(fromAmt);
        //System.out.println(fromAmt);
        //System.out.println(toAmt);
        to.setBalance(toAmt);
        accountDB.save(from);
        accountDB.save(to);


        Transactions transactions=new Transactions();
        transactions.setAccNum(from.getAccountNumber());
        transactions.setTransactionDate(LocalDateTime.now());
        transactions.setAmount(from.getBalance());
        transactionDB.save(transactions);

        Transactions transactions1=new Transactions();
        transactions1.setAccNum(to.getAccountNumber());
        transactions1.setTransactionDate(LocalDateTime.now());
        transactions1.setAmount(to.getBalance());
        transactionDB.save(transactions1);


        return "money sent successfully";
    }

    @Override
    public Transactions getTransctionById(Long accNum) {
        return transactionDB.findByAccNum(accNum);
    }
}
