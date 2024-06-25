package com.bce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bce.model.Employee;
import com.bce.model.EmployeeCustom;

@Repository
public interface EmployeeRepositoory extends CrudRepository<Employee, Long> {

	// Using constructor
	@Query("select new com.bce.model.Employee(e.id,e.firstname) from #{#entityName} e")
	List<Employee> findAllCustomEmloyee();

	// Using interface
	@Query("select e from #{#entityName} e")
	List<EmployeeCustom> findAllCustomEmloyee1();

	// Using native query
	@Query(value = "select e.id,e.firstName from #{#entityName} e", nativeQuery = true)
	Object[][] findAllCustomEmloyee2();

}
