package com.bce.api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import com.bce.api.payload.Employee;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeEndPoints2 {

	static ResourceBundle getURL() {
		ResourceBundle bundle = ResourceBundle.getBundle("routes");
		return bundle;
	}

	public static Response createEmployee(Employee employee) {

		return given().contentType(ContentType.JSON).accept(ContentType.JSON).body(employee).when()
				.post(getURL().getString("emp_post_all_url"));

	}

	public static Response readEmployee(Long empId) {
		return given().pathParam("id", empId).when().get(getURL().getString("emp_get_by_id_uri"));

	}

	public static Response deleteEmployee(Long empId) {
		return given().pathParam("id", empId).when().delete(getURL().getString("emp_delete_by_id_uri"));

	}

	public static Response deleteAllEmployee() {
		return given().when().delete(getURL().getString("emp_delete_all_id_uri"));

	}

	public static Response readAllEmployee() {
		return given().when().get(getURL().getString("emp_get_all_url"));

	}

}
