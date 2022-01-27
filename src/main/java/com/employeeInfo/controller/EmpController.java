package com.employeeInfo.controller;

import org.springframework.web.bind.annotation.RequestBody;

import com.employeeInfo.DTOs.EmpRequestDto;
import com.employeeInfo.DTOs.EmpResponseDto;

public interface EmpController {
	public EmpResponseDto getEmployeesList();
	public void insertEmployeeInfo(@RequestBody EmpRequestDto empRequestDto);
}
