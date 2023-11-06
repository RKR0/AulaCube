package com.rk.AulaCube.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rk.AulaCube.dto.EmployeeDto;
import com.rk.AulaCube.exception.DepartmentNotFoundException;
import com.rk.AulaCube.exception.EmployeeNotFoundException;
import com.rk.AulaCube.model.Department;
import com.rk.AulaCube.model.Employee;
import com.rk.AulaCube.repository.DepartmentRepo;
import com.rk.AulaCube.repository.EmployeeRepo;
import com.rk.AulaCube.service.EmployeeService;
import com.rk.AulaCube.transformer.EmployeeTransformer;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	JavaMailSender javaMailSender;
	
	final EmployeeRepo employeeRepo;
	
	final DepartmentRepo departmentRepo;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepo employeeRepo,DepartmentRepo departmentRepo){
		this.employeeRepo = employeeRepo;
		this.departmentRepo=departmentRepo;
	}
	
	
	
	
	

	@Override
	public EmployeeDto createEmp(EmployeeDto employeeDto) {
		
		Optional<Department> department = departmentRepo.findById(employeeDto.getDepartmentId());
        
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Invalid Department Id !!!");
        }
		
		Employee e = EmployeeTransformer.EmployeeDtoToEmployee(employeeDto);
		
		Employee emp = employeeRepo.save(e);
		
	      //  send an email
        String text = "Hi! " + emp.getFirstName() + " \n\nIt is with great pleasure that I extend a warm welcome to you as the newest addition to our AulaCube Technologies Pvt Ltd family. Your presence here is a source of excitement for all of us. \n" +
                "\n Id: " + emp.getId() + 
                " \n First Name: "+emp.getFirstName() +
                " \n Last Name: "+emp.getLastName() +
                " \n Email: "+emp.getEmail() +
                " \n Department: "+emp.getDepartmentId() +
                
                "\n\n If there is anything you need or if you have any questions, please don't hesitate to reach out to any of us.";
                

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("kanna51198@gmail.com");
        simpleMailMessage.setTo(emp.getEmail());
        simpleMailMessage.setSubject("Congrats!! "+emp.getFirstName());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
		
		return EmployeeTransformer.EmployeeToEmployeeDto(emp);
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long empId) {
		
		Optional<Employee> employee = employeeRepo.findById(empId);
		
        if(employee.isEmpty()){
            throw new EmployeeNotFoundException("Invalid Employee Id Number!!!");
        }
        if(employeeDto.getDepartmentId()!=null) {
        Optional<Department> department = departmentRepo.findById(employeeDto.getDepartmentId());
        
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Invalid Department Id !!!");
        }
        }
        Employee e = employee.get();
        
        if(employeeDto.getFirstName()!=null)
        	e.setFirstName(employeeDto.getFirstName());
        if(employeeDto.getLastName()!=null)
        	e.setLastName(employeeDto.getLastName());
        if(employeeDto.getEmail()!=null)
        	e.setEmail(employeeDto.getEmail());
        if(employeeDto.getDepartmentId()!=null)
        	e.setDepartmentId(employeeDto.getDepartmentId());
		e.setCreatedAt(e.getCreatedAt());
		Employee emp = employeeRepo.save(e);
		
		return EmployeeTransformer.EmployeeToEmployeeDto(emp);
	}

	// delete Employee by using id
	@Override
	public void deleteEmployee(Long empId) {
		
		Optional<Employee> employee = employeeRepo.findById(empId);
		
        if(employee.isEmpty()){
            throw new EmployeeNotFoundException("Invalid Employee Id Number!!!");
        }
        
        employeeRepo.deleteById(empId);
		
	}

	
	// get all employees
	@Override
	public List<EmployeeDto> getAllEmployees() {
		
		List<Employee> employees = employeeRepo.findAll();
		
		List<EmployeeDto> ans = new ArrayList<>();
		
		for(Employee e:employees) {
			
			EmployeeDto Emp = EmployeeTransformer.EmployeeToEmployeeDto(e);
			
			ans.add(Emp);
		}
		
		return ans;
	}

	// fetch Employee by using id
	@Override
	public EmployeeDto getEmployeeById(Long empId) {
		
		Optional<Employee> employee = employeeRepo.findById(empId);
		
        if(employee.isEmpty()){
            throw new EmployeeNotFoundException("Invalid Employee Id Number!!!");
        }
        
        return EmployeeTransformer.EmployeeToEmployeeDto(employee.get());
	}

}
