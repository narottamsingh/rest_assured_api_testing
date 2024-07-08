package com.bce.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bce.api.endpoints.EmployeeEndPoints;
import com.bce.api.payload.Employee;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class EmployeeTests {

	Faker faker;
	Employee empPayload;

	private Logger logger;

	@BeforeClass
	public void setup() {
		logger = LogManager.getLogger(this.getClass());
		faker = new Faker();
		empPayload = new Employee();

		empPayload.setId(faker.random().nextLong());
		empPayload.setFirstname(faker.name().firstName());
		empPayload.setFirstname(faker.name().lastName());
		empPayload.setEmail(faker.internet().emailAddress());
		// empPayload.setDob(faker.date().birthday());
	}

	@Test(priority = 1)
	public void testCreateEmployee() {
		logger.info("********Employee Creating**************");
		Response response = EmployeeEndPoints.createEmployee(empPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********Employee Created**************");
	}

	@Test
	public void testGetAllEmployee() {
		Response response = EmployeeEndPoints.readAllEmployee();
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

	}

}
