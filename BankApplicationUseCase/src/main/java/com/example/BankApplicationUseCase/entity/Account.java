package com.example.BankApplicationUseCase.entity;

import com.example.BankApplicationUseCase.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "Account_Details")
public class Account {
    @Id
    private long accountNumber;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String bankName;





}
