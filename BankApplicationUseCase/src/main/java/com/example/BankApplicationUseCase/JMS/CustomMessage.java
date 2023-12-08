package com.example.BankApplicationUseCase.JMS;

import com.example.BankApplicationUseCase.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomMessage implements Serializable {

    private String messageId;
    private Date messageDate;
    private Double money;
    private Long accNum;


}
