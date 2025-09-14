package com.example.BankSubModel.JMS;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomMessage implements Serializable {
    @Id
    private String messageId;
    private Date messageDate;
    private Double money;
    private Long accNum;
}
