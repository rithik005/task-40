package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	private EmployeeRepository repository;
	
	public EmployeeController(EmployeeRepository repository)
	{
		this.repository=repository;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return repository.save(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return repository.findById(id).orElse(null);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee)
	{
		Employee oldEmployee = repository.findById(id).orElse(null);
		
		oldEmployee.setName(employee.getName());
		oldEmployee.setEmail(employee.getEmail());
		oldEmployee.setDepartment(employee.getDepartment());
		oldEmployee.setSalary(employee.getSalary());
		
		return repository.save(oldEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		repository.deleteById(id);
		
		return "Employee Deleted Successfully";
	}
}