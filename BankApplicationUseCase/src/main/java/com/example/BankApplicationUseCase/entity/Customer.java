package com.example.BankApplicationUseCase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer_details")
public class Customer {
    @Id
    private String custId;
    private String fname;
    private String lname;
    private Integer age;
    private String gender;
    private Long number;
    private Long adar;
    private String email;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Account> accounts;
}
