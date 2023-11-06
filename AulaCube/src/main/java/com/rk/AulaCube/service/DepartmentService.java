package com.rk.AulaCube.service;



import java.util.List;

import com.rk.AulaCube.dto.DepartmentDto;

import jakarta.validation.Valid;


public interface DepartmentService {

	DepartmentDto createDep(@Valid DepartmentDto departmentDto);

	DepartmentDto updateDepartment(DepartmentDto departmentDto, String depId);

	void deleteDepartment(String depId);

	List<DepartmentDto> getAllDepartments();
	
	DepartmentDto getDepartmentById(String depId);

}
