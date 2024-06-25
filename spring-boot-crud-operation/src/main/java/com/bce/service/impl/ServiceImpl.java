package com.bce.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bce.model.Employee;
import com.bce.model.EmployeeCustom;
import com.bce.repository.EmployeeRepositoory;
import com.bce.service.EmployeeService;

@Service
public class ServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepositoory employeeRepositoory;

	@Override
	@CacheEvict(allEntries = true, cacheNames = "employee")
	public void saveOrUpdate(Employee employee) {
		employeeRepositoory.save(employee);
	}

	@Override
	@Cacheable("employee")
	public List<Employee> getAllEmployee() {
		return (List<Employee>) employeeRepositoory.findAll();

	}

	@Override
	@CachePut(cacheNames = "employee")
	public Employee getEmployeeById(long id) {
		Employee employee = employeeRepositoory.findById(id).get();
		return employee;
	}

	@Override
	@CacheEvict(cacheNames = "employee")
	public void delete(long id) {

		employeeRepositoory.deleteById(id);

	}

	@Override
	public List<Employee> getAllCustomerEmployeeData() {
		return employeeRepositoory.findAllCustomEmloyee();
	}

	@Override
	public List<EmployeeCustom> getAllCustomerEmployeeData1() {
		return employeeRepositoory.findAllCustomEmloyee1();
	}

	@Override
	public List<Employee> getAllCustomerEmployeeData2() {
		Object[][] obj = employeeRepositoory.findAllCustomEmloyee2();
		List<Employee> empList = new ArrayList<Employee>();
		for (Object[] o : obj) {
			empList.add(new Employee(Long.parseLong(o[0].toString()), String.valueOf(o[1])));
		}
		return empList;
	}

	@Override
	public void deleteAll() {
		employeeRepositoory.deleteAll();	
	}

}
