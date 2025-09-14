package com.example.BankApplicationUseCase.service;

import com.example.BankApplicationUseCase.entity.Account;
import com.example.BankApplicationUseCase.entity.Customer;
import com.example.BankApplicationUseCase.entity.Transactions;
import com.example.BankApplicationUseCase.entity.enums.AccountType;
import com.example.BankApplicationUseCase.repository.AccountDB;
import com.example.BankApplicationUseCase.repository.CustomerDB;
import com.example.BankApplicationUseCase.repository.TransactionDB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private ServiceI serviceI;

    @MockBean
    private CustomerDB customerDB;

    @MockBean
    private TransactionDB transactionDB;

    @MockBean
    private AccountDB accountDB;

    @Test
    public void saveCustomerTest(){
        Account account=new Account(123,2000.0, AccountType.Savings,"SBI");
        String customerId= UUID.randomUUID().toString();
        Customer user=new Customer(customerId,"temp","t",21,"male", 123453243L,32134L,"t@gmail.com", List.of(account));
        when(customerDB.save(user)).thenReturn(user);

        Customer cust=customerDB.save(user);
        assertEquals(user.getCustId(),cust.getCustId());


    }


    @Test
    public void saveTransactionTest(){
        Transactions t=new Transactions(123456L, LocalDateTime.now(),5000.0,123456L);
        when(transactionDB.save(t)).thenReturn(t);

        Transactions t1=transactionDB.save(t);
        assertEquals(t.getTransactionId(),t1.getTransactionId());

    }

    @Test
    void testGetAllCustomer() {

        Account account=new Account(123,2000.0, AccountType.Savings,"SBI");
        String customerId= UUID.randomUUID().toString();
        Customer user1=new Customer(customerId,"temp","t",21,"male", 123453243L,32134L,"t@gmail.com", List.of(account));

        Account account1=new Account(124,5000.0, AccountType.Current,"SBI");
        String customerId1= UUID.randomUUID().toString();
        Customer user2=new Customer(customerId1,"temp2","tt",22,"female", 12345543L,343213544L,"tt@gmail.com", List.of(account));
        List<Customer> mockCustomers = Arrays.asList(user1, user2);

        when(customerDB.findAll()).thenReturn(mockCustomers);

        List<Customer> result = serviceI.getAllCustomer();
        assertEquals(mockCustomers.size(), result.size());

    }


}
