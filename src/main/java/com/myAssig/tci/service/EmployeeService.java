package com.myAssig.tci.service;

import com.myAssig.tci.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    void saveEmployees(List<Employee> employees);
    List<Employee> getEligibleEmployees(LocalDate date);
}
