package com.myAssig.tci.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CurrencyResponse {
    private String currency;
    private List<EmployeeAmountResponse> employees;
}
