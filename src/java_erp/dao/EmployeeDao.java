package java_erp.dao;

import java.util.ArrayList;

import java_erp.dto.Employee;

public interface EmployeeDao {
	ArrayList<Employee> selectEmployeeByAll();
	Employee selectDepartmentByNo(Employee emp);
	
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(Employee emp);
}
