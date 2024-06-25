package com.bce.api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.bce.api.utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.bce.api.endpoints.EmployeeEndPoints;
import com.bce.api.payload.Employee;

import io.restassured.response.Response;

import java.util.List;

public class EmployeeDDTests {

	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testCreateEmployee(String Id,String firstName,String lastName, String email) {
		
		Employee empPayload=new Employee();
		empPayload.setId(Long.parseLong(Id));
		empPayload.setFirstname(firstName);
		empPayload.setLastname(lastName);
		empPayload.setEmail(email);
		
		Response response = EmployeeEndPoints.createEmployee(empPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2,dataProvider = "EmpIds",dataProviderClass = DataProviders.class)
	public void testGetEmployeeById(String Id) {
		Response response = EmployeeEndPoints.readEmployee(Long.parseLong(Id));
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3,dataProvider = "EmpIds",dataProviderClass = DataProviders.class)
	public void testDeleteEmployeeById(String Id) {
		Response response = EmployeeEndPoints.deleteEmployee(Long.parseLong(Id));
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteAllEmployeeFromDB() {
		// Fetch all employees
		Response responseEmp = EmployeeEndPoints.readAllEmployee();

		// Parse the response to get the list of employees
		List<Employee> empList = responseEmp.jsonPath().getList(".", Employee.class);
		for (Employee e:empList)
		{
			Response response = EmployeeEndPoints.deleteEmployee(e.getId());
			response.then().log().all();

			// Verify the response status code
			AssertJUnit.assertEquals(response.getStatusCode(), 200);
		}

	}

	@Test(priority = 4)
	public void testDeleteAllEmployee() {
		Response response = EmployeeEndPoints.deleteAllEmployee();
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

}
