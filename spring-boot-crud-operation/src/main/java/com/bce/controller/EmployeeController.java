package com.bce.controller;

import com.bce.exceptions.EmployeeNotFoundException;
import com.bce.model.Employee;
import com.bce.model.EmployeeCustom;
import com.bce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")

    public void saveEmployee(@RequestBody Employee employee) {
        employeeService.saveOrUpdate(employee);
    }

    @GetMapping("/employee/fetch/all")
    public List<Employee> findAll() {
        return employeeService.getAllEmployee();
    }


    @DeleteMapping("/employee/delete/all")
    public ResponseEntity<String> deleteAll() {
        employeeService.deleteAll();
        return new ResponseEntity<>("All Employee deleted successfully", HttpStatus.OK);
    }


    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        @SuppressWarnings("unused")
        Employee employee;
        try {
            employee = employeeService.getEmployeeById(id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
        employeeService.delete(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/employee/{id}/{dob}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @PathVariable String dob) {
        Employee employee;
        try {
            employee = employeeService.getEmployeeById(id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
        employee.setDob(LocalDate.parse(dob));
        employee.setId(id);
        employeeService.saveOrUpdate(employee);
        return new ResponseEntity<>("Employee updated succesfully", HttpStatus.OK);
    }

    @GetMapping("/employee/fetch/{id}")
    public Employee findEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employeecustom")
    public List<Employee> findAllCustomEmployeeData() {
        return employeeService.getAllCustomerEmployeeData();
    }

    @GetMapping("/employeecustom1")
    public List<EmployeeCustom> findAllCustomEmployeeData1() {
        return employeeService.getAllCustomerEmployeeData1();
    }

    @GetMapping("/employeecustom2")
    public List<Employee> findAllCustomEmployeeData2() {
        return employeeService.getAllCustomerEmployeeData2();
    }

}
