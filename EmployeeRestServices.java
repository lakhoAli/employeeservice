package com.etek.employee.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/employees")
public class EmployeeRestServices {

private final EmployeeRepository employeeRepository;
	
	public EmployeeRestServices(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping
	public Iterable<Employee> getAllEmployees(){
		return this.employeeRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if (employee.isPresent()) {
		      return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	}
	@CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping("/add")
	  public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
	    try {
	      Employee empObj = employeeRepository.save(employee);
	      return new ResponseEntity<>(empObj, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
