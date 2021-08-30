package java_erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java_erp.dao.DepartmentDao;
import java_erp.dto.Department;
import java_erp.util.jdbcUtil;

public class DepartmentDaoImpl02 implements DepartmentDao {
	private static final DepartmentDaoImpl02 instance = new DepartmentDaoImpl02();
	
	public static DepartmentDaoImpl02 getInstance() {
		return instance;
	}

	private DepartmentDaoImpl02() {
		// TODO Auto-generated method stub
	}

	@Override
	public ArrayList<Department> selectDepartmentByAll() {
		String sql = "select deptno, deptname, floor from department";
		ArrayList<Department> list = null;
		try(
			Connection con = jdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			list = new ArrayList<>();
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		String sql = "select deptno, deptname, floor from department where deptno = ?";
		try
		(
			Connection con = jdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		)
		{
			pstmt.setInt(1, department.getDeptNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		//deptno, deptname, floor
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		Department newDept = new Department(deptNo, deptName, floor);
		return newDept;
	}
	
	@Override
	public int insertDepartment(Department department) {
		
		String sql = "insert into department values(?, ?, ?)";	
		try
		(
			Connection con = jdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		)
		{
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department department) {
		// 1. DB 접속(시간이 많이 걸림) <- Connection Pool
		// 2. SQL을 Database에 맞는 명령문으로 준비(스트링을 -> 명령문)
		// [3. SQL에서 ?를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		// 4. SQL명령문 실행(select : executeQuery
		//				  Insert, Update, delete : executeUpdate)
		// [5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환]
		String sql = "update department set deptname = ?, floor = ? where deptno = ?";
		try(Connection con = jdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setInt(3, department.getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(Department department) {
		// 1. DB 접속(시간이 많이 걸림) <- Connection Pool
		// 2. SQL을 Database에 맞는 명령문으로 준비(스트링을 -> 명령문)
		// [3. SQL에서 ?를 입력 매개변수 값으로 치환해서 SQL명령문을 완성]
		// 4. SQL명령문 실행(select : executeQuery
		//				  Insert, Update, delete : executeUpdate)
		// [5. 만약 executeQuery일 경우 : SQL결과(ResultSet)를 클래스로 변환]
		String sql = "delete from department where deptno = ?";
		try(Connection con = jdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, department.getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
