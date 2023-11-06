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

import com.rk.AulaCube.dto.DepartmentDto;
import com.rk.AulaCube.dto.EmployeeDto;
import com.rk.AulaCube.service.DepartmentService;
import com.rk.AulaCube.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	  final DepartmentService departmentService;

	    @Autowired
	    public DepartmentController(DepartmentService departmentService){
	      this.departmentService = departmentService;
	  }
		

		// Add a new Department
		@PostMapping("/add")
		public ResponseEntity createDep(@Valid  @RequestBody DepartmentDto departmentDto){
			

				DepartmentDto createdeppdto = departmentService.createDep(departmentDto);
				return new ResponseEntity(createdeppdto,HttpStatus.CREATED);
			
		}
		
		
		// Update Exciting Department 
		@PutMapping("depId/{depId}")
		public ResponseEntity updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable String depId){
			
			try {
				DepartmentDto updateDep = departmentService.updateDepartment(departmentDto, depId);
				return new ResponseEntity(updateDep,HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
			
			
		}
		
		// Delete Department
		@DeleteMapping("depId/{depId}")
		public ResponseEntity deleteDepartment(@PathVariable String depId){
			
			try {
				departmentService.deleteDepartment(depId);
				return new ResponseEntity("Department Deleted Sucessfully!!",HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		
		// get all Departments
		@GetMapping("/")
		public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
			
			return ResponseEntity.ok(departmentService.getAllDepartments());
		}
		
		
		
		// get Department by Id
		@GetMapping("depId/{depId}")
		public ResponseEntity getDepartmentById(@PathVariable String depId){
			
			try {
				DepartmentDto emp = departmentService.getDepartmentById(depId);
				return new ResponseEntity(emp,HttpStatus.OK);
			}
			catch(Exception e){
				return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
}
