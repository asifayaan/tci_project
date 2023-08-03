package com.myAssig.tci.controller;

import com.myAssig.tci.entity.Employee;
import com.myAssig.tci.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tci")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee-bonus")
    public ResponseEntity<String> saveEmployees(@RequestBody List<Employee> employees) {
        employeeService.saveEmployees(employees);
        return ResponseEntity.ok("Employees saved successfully");
    }


    @GetMapping("/employee-bonus")
    public ResponseEntity<List<CurrencyResponse>> getEligibleEmployees(@RequestParam("date") @DateTimeFormat(pattern = "MMM-dd-yyyy") LocalDate date) {
        List<Employee> eligibleEmployees = employeeService.getEligibleEmployees(date);
        List<CurrencyResponse> response = eligibleEmployees.stream()
                .filter(employee -> employee.getExitDate() == null || employee.getExitDate().isAfter(date))
                .collect(Collectors.groupingBy(Employee::getCurrency))
                .entrySet()
                .stream()
                .map(entry -> new CurrencyResponse(entry.getKey(), entry.getValue()
                        .stream()
                        .map(employee -> new EmployeeAmountResponse(employee.getEmpName(), employee.getAmount()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}


