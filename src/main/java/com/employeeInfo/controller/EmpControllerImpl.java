package com.employeeInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeInfo.DTOs.EmpRequestDto;
import com.employeeInfo.DTOs.EmpResponseDto;
import com.employeeInfo.services.EmpServiceLayer;

@Controller
@RestController
@RequestMapping(path="/api")
public class EmpControllerImpl implements EmpController{
	 
	@Autowired
	private EmpServiceLayer empServiceLayer;
	
	@GetMapping("/employees")
	public EmpResponseDto getEmployeesList() {
		return empServiceLayer.getEmployeeList();
	}
	
	@PostMapping(path = "/insertEmployee")
	public void insertEmployeeInfo(@RequestBody EmpRequestDto empRequestDto) {
		empServiceLayer.storeToEmployeeTable(empRequestDto);
		return;
	}
	
	@GetMapping
	public String check() {
		return "Hello world";
	}
	
	@PutMapping("/updateEmployee")
	public void updateTable(@RequestBody EmpRequestDto empRequestDto) {
		empServiceLayer.updateEmployeeTable(empRequestDto);
		return;
	}
}
