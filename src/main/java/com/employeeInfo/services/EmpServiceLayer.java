package com.employeeInfo.services;

import com.employeeInfo.DTOs.EmpRequestDto;
import com.employeeInfo.DTOs.EmpResponseDto;

public interface EmpServiceLayer {
	public EmpResponseDto getEmployeeList();
	public void storeToEmployeeTable(EmpRequestDto empRequestDto);
	public void updateEmployeeTable(EmpRequestDto empRequestDto);
}
