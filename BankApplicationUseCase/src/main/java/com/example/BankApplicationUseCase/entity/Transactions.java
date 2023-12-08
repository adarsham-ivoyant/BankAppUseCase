package com.example.BankApplicationUseCase.entity;

import com.example.BankApplicationUseCase.entity.enums.AccountType;
import com.example.BankApplicationUseCase.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private LocalDateTime transactionDate;
    private Long fromAccNum;
    private Long toAccNum;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionTypeSender;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionTypeReciver;


}
