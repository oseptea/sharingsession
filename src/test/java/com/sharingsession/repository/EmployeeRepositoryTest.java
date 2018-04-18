package com.sharingsession.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sharingsession.model.Employee;
import com.sharingsession.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest 
public class EmployeeRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		/** given **/
	    Employee alex = new Employee("alex");
	    entityManager.persist(alex);
	    entityManager.flush();
	 
	    /** when **/
	    Employee found = employeeRepository.findByName(alex.getName());
	 
	    /** then **/
	    Assert.assertEquals(alex.getName(), found.getName());
	}
}
