package com.rk.AulaCube.service;

import java.util.List;


import com.rk.AulaCube.dto.EmployeeDto;


public interface EmployeeService {

	EmployeeDto createEmp(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(EmployeeDto employeeDto, Long empId);

	void deleteEmployee(Long empId);

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployeeById(Long empId);

}
