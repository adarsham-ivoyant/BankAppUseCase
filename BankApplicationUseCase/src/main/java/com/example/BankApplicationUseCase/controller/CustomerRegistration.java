package com.example.BankApplicationUseCase.controller;

import com.example.BankApplicationUseCase.entity.Customer;
import com.example.BankApplicationUseCase.entity.Transactions;
import com.example.BankApplicationUseCase.service.ServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRegistration {
    @Autowired
    public ServiceI custService;

    @PostMapping("/CustomerReg")
    public String custReg(@RequestBody Customer customer){
        custService.customerReg(customer);
        return "Registration successful";
    }

    @GetMapping("/GetAllCustomer")
    public List<Customer> getAllCustomer(){
        return custService.getAllCustomer();
    }

    @PostMapping("transferMoney/{fromAccountNum}/{ToAccountNum}/{amount}")
    public String moneyTransfer(@PathVariable("fromAccountNum") Long accountNumberFrom, @PathVariable("ToAccountNum") Long accountNumberTo, @PathVariable("amount") Double amount){
        String s=custService.moneyTransfer(accountNumberFrom,accountNumberTo,amount);
        return "transfer successfully";
    }

    @GetMapping("/getTransactionByAccountNumber/{accNum}")
        public Transactions getTransactionData(@PathVariable("accNum") Long accNum){
            return custService.getTransctionById(accNum);
        }

}
