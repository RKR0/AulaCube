package com.rk.AulaCube.model;



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String firstName;
	
	String lastName; 
	
	@Column(unique=true)
	String email; 
	
	
	String departmentId;
	
	@CreationTimestamp
	Timestamp createdAt;
	
	@UpdateTimestamp
	Timestamp updatedAt;

}
