package com.employeeInfo.repoLayers;
import static com.employeeInfo.constants.EmpQueries.GET_EMP_LIST;
import static com.employeeInfo.constants.EmpQueries.INSERT_EMP_INFO;
import static com.employeeInfo.constants.EmpEntityConstants.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.employeeInfo.entites.EmployeeEn;

@Repository
public class EmpRepoLayerImpl implements EmpRepoLayer {
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger LOGGER = LoggerFactory.getLogger(EmpRepoLayerImpl.class);
	
	@Override
	public List<EmployeeEn> getAllEmployeeInfo(){
		
		List<Map<String,Object>> queryRes;
		queryRes =  namedJdbcTemplate.queryForList(GET_EMP_LIST, new HashMap<>());
		LOGGER.info("The repo layer is called and the result is : {}",queryRes);
		return mapQueryResToEmployeeEn(queryRes);
	}
	
	private List<EmployeeEn> mapQueryResToEmployeeEn(List<Map<String,Object>> queryRes){
		
		if(CollectionUtils.isEmpty(queryRes)) {
			new ArrayList<>();
		}
		
		List<EmployeeEn> resList = new ArrayList<>();
		
		for(Map<String,Object> row : queryRes) {
			EmployeeEn employeeEn = new EmployeeEn();
			
			employeeEn.setEmpId(checkForNull(row,EMP_ID,String.class));
			employeeEn.setEmpFirstName(checkForNull(row,EMP_FIRST_NAME,String.class));
			employeeEn.setEmpLastName(checkForNull(row,EMP_LAST_NAME,String.class));
			employeeEn.setEmpAdress(checkForNull(row,EMP_ADRESS,String.class));
			employeeEn.setEmpDesignation(checkForNull(row,EMP_DESIGNATION,String.class));
			employeeEn.setSalary(checkForNull(row,SALARY,Float.class));
			
			resList.add(employeeEn);
		}
		
		return resList;
	}
	
	private static <T> T checkForNull(Map<String,Object> row ,String columnName,Class<T> type) {
		
		Object o = row.get(columnName);
		if(o!=null && type == String.class) {
			return (T)o;
		}
		if(o!=null && type == Float.class) {
			return (T)o;
		}
		return null;
	}
	
	@Override
	public int[] insertEmployee(List<EmployeeEn> employeeEnList) {
		if(CollectionUtils.isEmpty(employeeEnList))
			return null;
		
		LOGGER.info("Inserting {} row into Employee Table",employeeEnList.size());
		
		String sql = INSERT_EMP_INFO;
		return jdbcTemplate.batchUpdate(sql,
				new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps,int i) 
					throws SQLException{
				EmployeeEn employeeEn = employeeEnList.get(i);
				setPreparedStatement(ps,employeeEn);
			}
			
			@Override
			public int getBatchSize() {
				return employeeEnList.size();
			}
		});
	}
	
	//Set position,value
	void setPreparedStatement(PreparedStatement ps,EmployeeEn employeeEn) throws SQLException{
		ps.setObject(1, employeeEn.getEmpId(),java.sql.Types.VARCHAR);
		ps.setObject(2, employeeEn.getEmpFirstName(),java.sql.Types.VARCHAR);
		ps.setObject(3, employeeEn.getEmpLastName(),java.sql.Types.VARCHAR);
		ps.setObject(4, employeeEn.getEmpEmailId(),java.sql.Types.VARCHAR);
		ps.setObject(5, employeeEn.getEmpAdress(),java.sql.Types.VARCHAR);
		ps.setObject(6, employeeEn.getEmpDesignation(),java.sql.Types.VARCHAR);
		ps.setObject(7, employeeEn.getSalary(),java.sql.Types.FLOAT);
	}
	
	@Override
	public int updateEmployeeTable(EmployeeEn employeeEn) {
		StringBuilder sqlQuery = new StringBuilder();
		Map<String,Object> queryParam = new HashMap<>();
		
		sqlQuery.append("UPDATE employee_info SET ");
		if( !StringUtils.isEmpty(employeeEn.getEmpFirstName()) ){
			sqlQuery.append(EMP_FIRST_NAME + "= :" + EMP_FIRST_NAME + ", ");
			queryParam.put(EMP_FIRST_NAME,employeeEn.getEmpFirstName());
		}
		if( !StringUtils.isEmpty(employeeEn.getEmpLastName()) ){
			sqlQuery.append(EMP_LAST_NAME + "= :" + EMP_LAST_NAME + ", ");
			queryParam.put(EMP_LAST_NAME,employeeEn.getEmpLastName());
		}
		if( !StringUtils.isEmpty(employeeEn.getEmpEmailId()) ){
			sqlQuery.append(EMP_EMAIL_ID + "= :" + EMP_EMAIL_ID + ", ");
			queryParam.put(EMP_EMAIL_ID,employeeEn.getEmpEmailId());
		}
		if( !StringUtils.isEmpty(employeeEn.getEmpAdress()) ){
			sqlQuery.append(EMP_ADRESS + "= :" + EMP_ADRESS + ", ");
			queryParam.put(EMP_ADRESS,employeeEn.getEmpAdress());
		}
		if( !StringUtils.isEmpty(employeeEn.getEmpDesignation()) ){
			sqlQuery.append(EMP_DESIGNATION + "= :" + EMP_DESIGNATION + ", ");
			queryParam.put(EMP_DESIGNATION,employeeEn.getEmpDesignation());
		}
		sqlQuery.append(SALARY + " = :" + SALARY + " ");
		queryParam.put(SALARY,employeeEn.getSalary());
		
		sqlQuery.append("WHERE "+EMP_ID + "= :" + EMP_ID);
		queryParam.put(EMP_ID,employeeEn.getEmpId());
		
		String sql = sqlQuery.toString();
		
		return namedJdbcTemplate.update(sql, queryParam);
	}
}
