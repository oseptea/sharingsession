package com.sharingsession.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharingsession.model.Employee;
import com.sharingsession.service.IEmployeeService;

@RestController
@RequestMapping(EmployeeRestController.Constants.CONTROLLER_PATH_ADDR)
public class EmployeeRestController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping(EmployeeRestController.Constants.CREATE_OR_GET_EMPLOYEES_ADDR)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        HttpStatus status = HttpStatus.CREATED;
        Employee saved = employeeService.save(employee);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(EmployeeRestController.Constants.CREATE_OR_GET_EMPLOYEES_ADDR)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    public static class Constants {
    	private Constants() {}
    	public static final String CONTROLLER_PATH_ADDR = "/api";
    	public static final String CREATE_OR_GET_EMPLOYEES_ADDR = "/employees";
    }

}