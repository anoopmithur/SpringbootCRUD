package com.employeeInfo.constants;

import java.util.List;

import com.employeeInfo.entites.EmployeeEn;

public class EmpQueries {
	
	public static final String GET_EMP_LIST = "SELECT * FROM employee_info";
	
	public static final String INSERT_EMP_INFO = "INSERT INTO employee_info VALUES (?,?,?,?,?,?,?)";
}
