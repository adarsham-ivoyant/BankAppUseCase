package com.example.BankApplicationUseCase.service;

import com.example.BankApplicationUseCase.entity.Account;
import com.example.BankApplicationUseCase.entity.Customer;

import com.example.BankApplicationUseCase.entity.Transactions;
import com.example.BankApplicationUseCase.entity.enums.TransactionType;
import com.example.BankApplicationUseCase.exception.AccountNotFoundException;
import com.example.BankApplicationUseCase.exception.InsufficentBalance;
import com.example.BankApplicationUseCase.repository.AccountDB;
import com.example.BankApplicationUseCase.repository.CustomerDB;

import com.example.BankApplicationUseCase.repository.TransactionDB;
import com.opencsv.CSVWriter;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BankAppServiceImpl implements BankAppService {

    @Autowired
    public CustomerDB customerDB;

    @Autowired
    public AccountDB accountDB;

    @Autowired
    public TransactionDB transactionDB;

    @Override
    public String customerRegistration(Customer customer) {
        String customerId=UUID.randomUUID().toString();
          customer.setCustomerId(customerId);
          Customer savedCustomer = customerDB.save(customer);
          return savedCustomer.getCustomerId();
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerDB.findAll();
    }

    @Override
    public String addMoneyToAccount(Long accNumber, Double amount) {
        Account accountNum=accountDB.findById(accNumber).orElseThrow(AccountNotFoundException::new);
        Double totalAmount=accountNum.getBalance()+amount;
        accountNum.setBalance(totalAmount);
        accountNum.setAccountType(accountNum.getAccountType());
        accountDB.save(accountNum);
        return "Money Added Successfully";
    }

    @Override
    public String withdrawMoney(Long accountNumber, Double amount) {
        Account accountNum=accountDB.findById(accountNumber).orElseThrow(AccountNotFoundException::new);
        if(accountNum.getBalance()>2000){
            Double totalAmount=accountNum.getBalance()-amount;
            //System.out.println(totalAmount);
            accountNum.setBalance(totalAmount);
            accountDB.save(accountNum);
            return "withdrawSuccessfully";
        }
        else{
            System.out.println("Minimum balance");
        }
        return null;

    }

    @Override
    public String moneyTransfer(Long fromAccNum, Long toAccNum, Double amount) {
        Account fromAccNumber=accountDB.findById(fromAccNum).orElseThrow(AccountNotFoundException::new);
        Account toAccNumber=accountDB.findById(toAccNum).orElseThrow(AccountNotFoundException::new);

        if(fromAccNumber.getBalance().compareTo(amount)<0){
            throw new InsufficentBalance();
        }

        Double fromAmount=fromAccNumber.getBalance() - amount;
        Double toAmount=toAccNumber.getBalance() + amount;



        fromAccNumber.setBalance(fromAmount);
        toAccNumber.setBalance(toAmount);
        accountDB.save(fromAccNumber);


        Transactions transactions=new Transactions();
        transactions.setTransactionTypeReciver(TransactionType.CREDIT);
        transactions.setTransactionTypeSender(TransactionType.DEBIT);
        transactions.setTransactionDate(LocalDateTime.now());
        transactions.setFromAccNum(fromAccNum);
        transactions.setToAccNum(toAccNum);
        transactionDB.save(transactions);



        return "money sent successfully";
    }

    @Override
    public List<Transactions> getAllTransction() {
        return transactionDB.findAll();
    }

    @Override
    public List<Transactions> searchTransactionByAccountNumber(Long accountNum) {
        return transactionDB.findByFromAccNumOrToAccNum(accountNum,accountNum);
    }

    //Shedular code
    @Override
    //@Scheduled(fixedRate = 30000)
    @Scheduled(cron="*/2 * * * * ")
    public void generateTransactionsCSV() {
        List<Transactions> transactions = transactionDB.findAll();

        String filePath = "transactions.csv";

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
            String[] header = {"TransactionId", "Date", "FromAccNum","ToAccNum"};
            csvWriter.writeNext(header);


            for (Transactions transaction : transactions) {
                String[] data = {
                        String.valueOf(transaction.getTransactionId()),
                        String.valueOf(transaction.getTransactionDate()),
                        String.valueOf(transaction.getFromAccNum()),
                        String.valueOf(transaction.getToAccNum())
                };
                csvWriter.writeNext(data);
            }

            System.out.println("Transactions CSV file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

