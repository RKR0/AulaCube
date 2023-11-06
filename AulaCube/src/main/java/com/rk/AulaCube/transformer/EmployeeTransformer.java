package com.rk.AulaCube.transformer;

import com.rk.AulaCube.dto.EmployeeDto;
import com.rk.AulaCube.model.Employee;

public class EmployeeTransformer {

	// Convert EmployeeDto Class to Employee Class
    public static Employee EmployeeDtoToEmployee(EmployeeDto employeeDto){

        return Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .departmentId(employeeDto.getDepartmentId())
                .build();
    }
    
    // Convert Employee Class to EmployeeDto Class
    public static EmployeeDto EmployeeToEmployeeDto(Employee employee){

        return EmployeeDto.builder()
        		.id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .departmentId(employee.getDepartmentId())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }
}

