package com.myAssig.tci.service;

import com.myAssig.tci.entity.Employee;
import com.myAssig.tci.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> getEligibleEmployees(LocalDate date) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getJoiningDate().isBefore(date) && (employee.getExitDate() == null || employee.getExitDate().isAfter(date)))
                .sorted(Comparator.comparing(Employee::getEmpName))
                .collect(Collectors.toList());
    }
}