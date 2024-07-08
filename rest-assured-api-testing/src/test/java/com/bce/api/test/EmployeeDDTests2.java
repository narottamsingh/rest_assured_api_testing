package com.bce.api.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.bce.api.endpoints.EmployeeEndPoints2;
import com.bce.api.payload.Employee;
import com.bce.api.utilities.DataProviders;

import io.restassured.response.Response;

public class EmployeeDDTests2 {

	Logger logger=LogManager.getLogger(this.getClass());
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testCreateEmployee(String Id,String firstName,String lastName, String email) {
		logger.info("*** Employee Creating** {} {} {} {} ",Id,firstName,lastName,email);
		Employee empPayload=new Employee();
		empPayload.setId(Long.parseLong(Id));
		empPayload.setFirstname(firstName);
		empPayload.setLastname(lastName);
		empPayload.setEmail(email);
		
		Response response = EmployeeEndPoints2.createEmployee(empPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("**** Employee created : status is {}",response.getStatusCode());
	}

	@Test(priority = 2,dataProvider = "EmpIds",dataProviderClass = DataProviders.class)
	public void testGetEmployeeById(String Id) {
		Response response = EmployeeEndPoints2.readEmployee(Long.parseLong(Id));
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3,dataProvider = "EmpIds",dataProviderClass = DataProviders.class)
	public void testDeleteEmployeeById(String Id) {
		Response response = EmployeeEndPoints2.deleteEmployee(Long.parseLong(Id));
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteAllEmployeeFromDB() {
		// Fetch all employees
		Response responseEmp = EmployeeEndPoints2.readAllEmployee();

		// Parse the response to get the list of employees
		List<Employee> empList = responseEmp.jsonPath().getList(".", Employee.class);
		for (Employee e:empList)
		{
			Response response = EmployeeEndPoints2.deleteEmployee(e.getId());
			response.then().log().all();

			// Verify the response status code
			AssertJUnit.assertEquals(response.getStatusCode(), 200);
		}

	}	

	@Test(priority = 4)
	public void testDeleteAllEmployee() {
		Response response = EmployeeEndPoints2.deleteAllEmployee();
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

}
