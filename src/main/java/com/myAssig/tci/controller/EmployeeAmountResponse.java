package com.myAssig.tci.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeAmountResponse {
    private String empName;
    private double amount;
}
