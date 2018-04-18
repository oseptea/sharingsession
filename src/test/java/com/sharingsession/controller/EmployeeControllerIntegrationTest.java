package com.sharingsession.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sharingsession.controller.EmployeeRestController;
import com.sharingsession.model.Employee;
import com.sharingsession.service.IEmployeeService;
import com.sharingsession.utils.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRestController.class)
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {
	
	@Configuration
	public static class testConf {
		@Bean
		public EmployeeRestController employeeRestController() {
			return new EmployeeRestController();
		}
	}

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IEmployeeService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostEmployee_thenCreateEmployee() throws Exception {
        Employee alex = new Employee("alex");
        given(service.save(Mockito.anyObject())).willReturn(alex);

		mvc.perform(post(EmployeeRestController.Constants.CONTROLLER_PATH_ADDR + EmployeeRestController.Constants.CREATE_OR_GET_EMPLOYEES_ADDR)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(alex)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is("alex")));
        verify(service, VerificationModeFactory.times(1)).save(Mockito.anyObject());
        reset(service);
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        Employee alex = new Employee("alex");
        Employee john = new Employee("john");
        Employee bob = new Employee("bob");

        List<Employee> allEmployees = Arrays.asList(alex, john, bob);

        given(service.getAllEmployees()).willReturn(allEmployees);

		mvc.perform(get(EmployeeRestController.Constants.CONTROLLER_PATH_ADDR + EmployeeRestController.Constants.CREATE_OR_GET_EMPLOYEES_ADDR)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].name", is(alex.getName())))
				.andExpect(jsonPath("$[1].name", is(john.getName())))
				.andExpect(jsonPath("$[2].name", is(bob.getName())));
        verify(service, VerificationModeFactory.times(1)).getAllEmployees();
        reset(service);
    }

}