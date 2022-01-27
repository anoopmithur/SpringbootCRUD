package com.employeeInfo.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.employeeInfo.entites.EmployeeEn;

public class EmpResponseDto {
	
	List<EmployeeEn> resList;

	public List<EmployeeEn> getResList() {
		if(resList == null) {
			resList = new ArrayList<>();
		}
		return resList;
	}

	public void setResList(List<EmployeeEn> resList) {
		this.resList = resList;
	}
	

}
