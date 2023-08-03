package com.myAssig.tci.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empName;
    private String department;
    private int amount;
    private String currency;
    private LocalDate joiningDate;
    private LocalDate exitDate;
}

