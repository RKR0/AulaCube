package com.rk.AulaCube.transformer;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.rk.AulaCube.dto.DepartmentDto;

import com.rk.AulaCube.model.Department;


public class DepartmentTransformer {

	// Convert DepartmentDto Class to Department Class
    public static Department DepartmentDtoToDepartment(DepartmentDto departmentDto){

        return Department.builder()
                .departmentId(departmentDto.getDepartmentId())
                .departmentName(departmentDto.getDepartmentName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    
    // Convert Department Class to DepartmentDto Class
    public static DepartmentDto DepartmentToDepartmentDto(Department department){

        return DepartmentDto.builder()
        		.departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .updatedAt(department.getUpdatedAt())
                .build();
    }
}

