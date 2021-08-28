package java_erp.dao;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_erp.dao.impl.DepartmentDaoImpl;
import java_erp.dto.Department;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	private DepartmentDao dao;

	@Before
	public void setUp() throws Exception {
		dao = DepartmentDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() {
		System.out.println("testSelectDepartmentbyAll()");
		ArrayList<Department> list = dao.selectDepartmentByAll();
		Assert.assertNotEquals(0, list.size());
		
		list.stream().forEach(System.out::println);
		
	}

	@Test
	public void test02SelectDepartmentByNo() {
		System.out.println("test02SelectDepartmentByNo()");
		Department selectDepartment = dao.selectDepartmentByNo(new Department(1));
		Assert.assertNotNull(selectDepartment);
		System.out.println(selectDepartment);
	}

	@Test
	public void test03InsertDepartment() {
		System.out.println("test03InsertDepartment()");
		Department newDepartment = (new Department(3, "개발", 9));
		int res = dao.insertDepartment(newDepartment);
		Assert.assertEquals(1, res);
		
		test01SelectDepartmentByAll();
	}

	@Test
	public void test04UpdateDepartment() {
		System.out.println("test04UpdateDepartment()");
		Department selectDepartment = dao.selectDepartmentByNo(new Department(3));
		selectDepartment.setDeptName("마케팅");
		
		int res = dao.updateDepartment(selectDepartment);
		Assert.assertEquals(1, res);
		
		test01SelectDepartmentByAll();
	}

	@Test
	public void test05DeleteDepartment() {
		System.out.println("test05DeleteDepartment()");
		int res = dao.deleteDepartment(new Department(3));
		Assert.assertEquals(1, res);
		
		test01SelectDepartmentByAll();		
	}

}
