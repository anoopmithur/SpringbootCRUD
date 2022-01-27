package com.employeeInfo.repoLayers;

import java.util.List;

import com.employeeInfo.entites.EmployeeEn;

public interface EmpRepoLayer {
	public List<EmployeeEn> getAllEmployeeInfo();
	public int[] insertEmployee(List<EmployeeEn> employeeEnList);
	public int updateEmployeeTable(EmployeeEn employeeEn);
}
