package com.rk.AulaCube.dto;


import java.sql.Timestamp;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	
	Long id;

	@NotEmpty
	@Pattern(regexp="([A-Za-z]*)",message="Please enter valid Name !!")
	String firstName;
	
	@Pattern(regexp="([A-Za-z]*)",message="Please enter valid Name !!")
	String lastName;
	
	@Email(message="Please enter valid email address !!")
	@NotEmpty(message="Please enter valid email address !!")
	String email;
	
	String departmentId;
	
	Timestamp updatedAt;
}
