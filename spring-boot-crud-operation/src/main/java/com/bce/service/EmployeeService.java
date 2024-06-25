package com.bce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bce.model.Employee;
import com.bce.model.EmployeeCustom;

@Service
public interface EmployeeService {

	void saveOrUpdate(Employee employee);

	List<Employee> getAllEmployee();

	Employee getEmployeeById(long id);

	void delete(long id);
	
	void deleteAll();

	List<Employee> getAllCustomerEmployeeData();

	List<EmployeeCustom> getAllCustomerEmployeeData1();

	List<Employee> getAllCustomerEmployeeData2();

}
