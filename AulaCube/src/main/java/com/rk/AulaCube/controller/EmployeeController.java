package com.rk.AulaCube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rk.AulaCube.dto.EmployeeDto;
import com.rk.AulaCube.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
      this.employeeService = employeeService;
  }
	

	// Add a new Employee
	@PostMapping("/add")
	public ResponseEntity createEmp(@Valid  @RequestBody EmployeeDto employeeDto){
		
		try {
			EmployeeDto createEmpdto = employeeService.createEmp(employeeDto);
			return new ResponseEntity(createEmpdto,HttpStatus.CREATED);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// Update Exciting Employee 
	@PutMapping("empId/{empId}")
	public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long empId){
		
		try {
			EmployeeDto updateEmp = employeeService.updateEmployee(employeeDto, empId);
			return new ResponseEntity(updateEmp,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	// Delete Employee
	@DeleteMapping("empId/{empId}")
	public ResponseEntity deleteEmployee(@PathVariable Long empId){
		
		try {
			employeeService.deleteEmployee(empId);
			return new ResponseEntity("Employee Deleted Sucessfully!!",HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	// get all Employees
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	
	
	// get Employee by Id
	@GetMapping("empId/{empId}")
	public ResponseEntity getEmployeeById(@PathVariable Long empId){
		
		try {
			EmployeeDto emp = employeeService.getEmployeeById(empId);
			return new ResponseEntity(emp,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
