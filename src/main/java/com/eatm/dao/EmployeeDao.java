package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	EntityManager entitymanager;

	@Autowired
	EntityTransaction entitytransaction;

	// add
	public Employee saveEmployee(Employee employee) {
		entitymanager.persist(employee);
		entitytransaction.begin();
		entitytransaction.commit();
		return employee;
	}

	// update
	public Employee updateEmployee(Employee employee) {
		entitymanager.merge(employee);
		entitytransaction.begin();
		entitytransaction.commit();
		return employee;
	}

	// delete
	public Employee deleteEmployee(Employee employee) {
		entitymanager.remove(employee);
		entitytransaction.begin();
		entitytransaction.commit();
		return employee;
	}

	// find Employee by id
	public Employee findEmployeeById(int employeeId) {
		return entitymanager.find(Employee.class, employeeId);
	}

	// find all employee
	public List<Employee> findAllEmployee() {
		Query query = entitymanager.createQuery("SELECT e FROM Employee e");
		return query.getResultList();
	}

	// find employee by email and password
	public Employee findEmployeeByEmailPassword(String email, String password) {
		Query query = entitymanager.createQuery("select e from Employee e where e.email=?1 and e.password=?2 ");
		query.setParameter(1, email);
		query.setParameter(2, password);

		List<Employee> employeeList = query.getResultList(); // in list there is only 1 employee

		if (employeeList.size() > 0) {
			return employeeList.get(0);
		}
		return null;
	}
	
	//find employee by email
	public Employee findEmployeeByEmail(String email) {
		Query query = entitymanager.createQuery("select e from Employee e where e.email=?1");
		query.setParameter(1, email);

		List<Employee> employeeList = query.getResultList(); // in list there is only 1 employee

		if (employeeList.size() > 0) {
			return employeeList.get(0);
		}
		return null;
	}

}

/*
 * 
 * Controller ---------------- service -------------------- Dao servlet methods
 * business logic(add,sub) db logic
 * 
 * 
 * in between these layers data has to be transfered We cannot use entity object
 * instead use dto object : dto (Data Transfer Object) dto is similar to entity
 * object without any mapping and hibernate annotation
 * 
 */