package com.sharingsession.service;

import java.util.List;

import com.sharingsession.model.Employee;

public interface IEmployeeService {

    public Employee getEmployeeById(Long id);

    public Employee getEmployeeByName(String name);

    public List<Employee> getAllEmployees();

    public boolean exists(String email);

    public Employee save(Employee employee);
    
}