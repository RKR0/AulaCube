package com.rk.AulaCube.serviceimpl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rk.AulaCube.dto.DepartmentDto;

import com.rk.AulaCube.exception.DepartmentNotFoundException;

import com.rk.AulaCube.model.Department;

import com.rk.AulaCube.repository.DepartmentRepo;

import com.rk.AulaCube.service.DepartmentService;
import com.rk.AulaCube.transformer.DepartmentTransformer;


import jakarta.validation.Valid;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	final DepartmentRepo departmentRepo;
	
	
	
	@Autowired
	public DepartmentServiceImpl(DepartmentRepo departmentRepo){
		this.departmentRepo = departmentRepo;
	}

	@Override
	public DepartmentDto createDep(@Valid DepartmentDto departmentDto) {
		
		Department dep = DepartmentTransformer.DepartmentDtoToDepartment(departmentDto);
		
		Department createddep = departmentRepo.save(dep);
        
        return DepartmentTransformer.DepartmentToDepartmentDto(createddep);
		
	}

	@Override
	public DepartmentDto updateDepartment(DepartmentDto departmentDto, String depId) {
		
		Optional<Department> department = departmentRepo.findById(depId);
		
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Invalid Deprtment Id !!!");
        }
        
        Department updateddepartment = department.get();
        updateddepartment.setDepartmentName(departmentDto.getDepartmentName());
      updateddepartment.setUpdatedAt(LocalDateTime.now());
        
        Department dep = departmentRepo.save(updateddepartment);
        
        return DepartmentTransformer.DepartmentToDepartmentDto(dep);
	}

	@Override
	public void deleteDepartment(String depId) {
		
		Optional<Department> department = departmentRepo.findById(depId);
		
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Invalid Deprtment Id !!!");
        }
        departmentRepo.deleteById(depId);
		
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		
		List<Department> employees = departmentRepo.findAll();
		
		List<DepartmentDto> ans = new ArrayList<>();
		
		for(Department e:employees) {
			
			DepartmentDto Emp = DepartmentTransformer.DepartmentToDepartmentDto(e);
			
			ans.add(Emp);
		}
		
		return ans;
	}

	@Override
	public DepartmentDto getDepartmentById(String depId) {
		
		Optional<Department> department = departmentRepo.findById(depId);
		
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Invalid Deprtment Id !!!");
        }
        
        return DepartmentTransformer.DepartmentToDepartmentDto(department.get());
	}

}
