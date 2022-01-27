package com.employeeInfo.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.employeeInfo.DTOs.EmpRequestDto;
import com.employeeInfo.DTOs.EmpResponseDto;
import com.employeeInfo.entites.EmployeeEn;
import com.employeeInfo.repoLayers.EmpRepoLayer;

@Service	
public class EmpServiceLayerImpl implements EmpServiceLayer {
	
	@Autowired
	private EmpRepoLayer empRepoLayer;
	
	@Override
	public EmpResponseDto getEmployeeList() {
		List<EmployeeEn> res = empRepoLayer.getAllEmployeeInfo();
		return mapEmployeeEnToEmpResponseDto(res);
	}
	
	private EmpResponseDto mapEmployeeEnToEmpResponseDto(List<EmployeeEn> res) {
		EmpResponseDto empResponseDto = new EmpResponseDto();
		empResponseDto.setResList(res);
		return empResponseDto;
	}
	
	@Override
	public void storeToEmployeeTable(EmpRequestDto empRequestDto) {
		EmployeeEn employeeEn = mapEmpRequestDtoTOEmployeeEn(empRequestDto);
		empRepoLayer.insertEmployee(Arrays.asList(employeeEn));
		return;
	}
	
	private EmployeeEn mapEmpRequestDtoTOEmployeeEn(EmpRequestDto empRequestDto) {
		EmployeeEn employeeEn = new EmployeeEn();
		
		employeeEn.setEmpId(empRequestDto.getEmpId());
		employeeEn.setEmpFirstName(empRequestDto.getEmpFirstName());
		employeeEn.setEmpLastName(empRequestDto.getEmpLastName());
		employeeEn.setEmpEmailId(empRequestDto.getEmpEmailId());
		employeeEn.setEmpAdress(empRequestDto.getEmpAdress());
		employeeEn.setEmpDesignation(empRequestDto.getEmpDesignation());
		employeeEn.setSalary(empRequestDto.getSalary());
		
		return employeeEn;
	}
	
	@Override
	public void updateEmployeeTable(EmpRequestDto empRequestDto) {
		EmployeeEn employeeEn = mapEmpRequestDtoTOEmployeeEn(empRequestDto);
		empRepoLayer.updateEmployeeTable(employeeEn);
		return;
	}
}
