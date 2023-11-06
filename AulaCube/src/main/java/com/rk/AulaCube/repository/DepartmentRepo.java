package com.rk.AulaCube.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rk.AulaCube.model.Department;


@Repository
public interface DepartmentRepo extends MongoRepository<Department,String>{

}
