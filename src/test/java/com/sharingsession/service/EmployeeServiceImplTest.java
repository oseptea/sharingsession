package com.sharingsession.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sharingsession.model.Employee;
import com.sharingsession.repository.EmployeeRepository;
import com.sharingsession.service.EmployeeServiceImpl;
import com.sharingsession.service.IEmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
 
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public IEmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }
 
    @Autowired
    private IEmployeeService employeeService;
 
    @MockBean
    private EmployeeRepository employeeRepository;
 
    @Before
    public void setUp() {
        Employee alex = new Employee("alex");
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);
        Assert.assertEquals(name, found.getName());
    }
    
}