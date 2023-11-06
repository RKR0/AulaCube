package com.rk.AulaCube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rk.AulaCube.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long>{

}
