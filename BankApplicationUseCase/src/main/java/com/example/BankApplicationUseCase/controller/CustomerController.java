package com.example.BankApplicationUseCase.controller;

import com.example.BankApplicationUseCase.entity.Customer;
import com.example.BankApplicationUseCase.entity.Transactions;
import com.example.BankApplicationUseCase.service.BankAppService;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    public BankAppService bankAppService;

    @PostMapping("/CustomerRegistration")
    public String customerRegistration(@RequestBody Customer customer){
        bankAppService.customerRegistration(customer);
        return "Registration successful";
    }

    @PostMapping("/AddMoneyToAccount/{accNum}/{amount}")
    public String addingMoneyToAccount(@PathVariable("accNum") Long accountNumber,
                                       @PathVariable("amount") Double amount){
        bankAppService.addMoneyToAccount(accountNumber,amount);
        return "Money Added Successfully";
    }
    @PostMapping("/withdrawMoney/{accNum}/{amount}")
    public String withdrawMoney(@PathVariable("accNum") Long accountNumber,
                                       @PathVariable("amount") Double amount){
        String statement=bankAppService.withdrawMoney(accountNumber,amount);
        return statement;
    }
    @GetMapping("/GetAllCustomer")
    public List<Customer> getAllCustomer(){
        return bankAppService.getAllCustomer();
    }

    @PostMapping("transferMoney/{fromAccountNum}/{ToAccountNum}/{amount}")
    public String moneyTransfer(@PathVariable("fromAccountNum") Long accountNumberFrom,
                                @PathVariable("ToAccountNum") Long accountNumberTo,
                                @PathVariable("amount") Double amount){
        String statement=bankAppService.moneyTransfer(accountNumberFrom,accountNumberTo,amount);
        return statement;
    }

    @GetMapping("GetAllTransactionDetails/{accNum}")
    public List<Transactions> getTransactionDetails(@PathVariable("accNum") Long accNum){
        return bankAppService.searchTransactionByAccountNumber(accNum);

    }

    @GetMapping("GetTransaction/")
    public List<Transactions> getAllTransaction(){
        return bankAppService.getAllTransction();
    }

    @GetMapping("TransactionDataForExcel")
    public void transactionData(){
        bankAppService.generateTransactionsCSV();
    }



}
