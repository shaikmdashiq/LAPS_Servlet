package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.LeaveType;
import model.Role;
import model.Staff;

public class StaffSql {
	
	public Connection getConnection()throws Exception
	{
		Connection conn= null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/laps","root","password");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public Staff getStaffById(String id)
	{
		Staff s = null;
		String sql= "select * from Staff where staffid="+"\""+id+"\"";
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				s = new Staff();
				s.setFirstname(rs.getString("firstname"));
				s.setPwd(rs.getString("pwd"));
				s.setRoleid(rs.getInt("roleid"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	
	//
	public String getNameById(String id)
	{
		String name= "";
		Staff s = null;
		String sql= "select firstname from Staff where staffid="+"\""+id+"\"";
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				s = new Staff();
				s.setFirstname(rs.getString("firstname"));
				name=s.getFirstname();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return name;
	}
	
	
	public String getPath(String id,String pwd)
	{
		String path="";
		Staff staff = getStaffById(id);
		if(null!=staff&&staff.getPwd().equals(pwd))
		{
			if(1==staff.getRoleid())
			{
				path="admin";
			}
			if(2==staff.getRoleid())
			{
				path="manager";
			}
			if(3==staff.getRoleid())
			{
				path="staff";
			}
		}else 
		{
			path="login.jsp";
		}
		return path;
	}
	
	//get all staff  .. for manage staff -->admin
	
	public ArrayList<Staff> getAllStaff()
	{
		String sql = "select * from Staff;";
		ArrayList<Staff> staff =null; 
				staff = new ArrayList<Staff>();
		RoleSql r = new RoleSql();
		try
		{
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Staff s = new Staff();
				s.setStaffid(rs.getString("staffid"));
				s.setRoleid(rs.getInt("roleid"));
				s.setFirstname(rs.getString("firstname"));
				s.setPwd(rs.getString("pwd"));
				s.setManagerid(rs.getString("managerid"));
				s.setCreatedby(rs.getString("createdby"));
				s.setUpdatedby(rs.getString("updatedby"));
				s.setTitle(r.getTitleById(rs.getInt("roleid")));
				staff.add(s);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return staff;
	}
	
	//update staff
	public void updateStaff(Staff staff)
	{
		
		String sql ="update staff set roleid="+staff.getRoleid()+",pwd='"+staff.getPwd()+"',managerid='"
					+staff.getManagerid()+"',updatedby='"+staff.getUpdatedby()+"' where staffid='"+staff.getStaffid()+"';";
		Connection conn = null;
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			String error = "Error on update type: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	public int getCompensation(String staffid)
	{
		Connection conn = null;
		int com = -1;
		String sql = "select compensation from Staff where staffid='"+staffid+"';";
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			ResultSet rs= st.executeQuery(sql);
			if(rs.next())
			{
				com = rs.getInt("compensation");
			}
			return com;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return com;
	}
	public void updateCompensation(String staffid)
	{
		int com = getCompensation(staffid);
		String sql ="update Staff set compensation="+(com+1)+" where staffid='"+staffid+"';" ;
		Connection conn = null;
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			String error = "Error on update Staff: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	//update -1
	public void updateCompensationone(String staffid)
	{
		int com = getCompensation(staffid);
		String sql ="";
		if(com>0){
			sql ="update Staff set compensation="+(com-1)+" where staffid='"+staffid+"';" ;
		}else{
			sql ="update Staff set compensation=0"+" where staffid='"+staffid+"';" ;
		}
		Connection conn = null;
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			String error = "Error on update Staff: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	//delete staff
	public void deleteStaff(String staffid)
	{
		String sql = "delete from Staff where staffid='"+staffid+"';";
		Connection conn = null;
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int rows = st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			String error = "Error on delete type: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	//insert new staff
	public void insertStaff(Staff staff)
	{
		String sql = "insert into Staff (staffid,roleid,firstname,lastname,gender,address,phoneno,email,pwd"
				+ ",managerid,createdby,updatedby) values"	
				+"('"+staff.getStaffid()+"',"+staff.getRoleid()+",'"+staff.getFirstname()+"','"+staff.getLastname()+"'"
				+",'"+staff.getGender()+"','"+staff.getAddress()+"',"+staff.getPhoneno()+",'"+staff.getEmail()+"','"
				+staff.getPwd()+"','"+staff.getManagerid()+"','"+staff.getCreatedby()+"','"+staff.getUpdatedby()+"');";
		Connection conn = null;
		try
		{
			conn = getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing insert: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		}catch (Exception e) {
			String error = "Error on insert new type: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	//get roleid by staffid
	public Integer getRoleIdByStaffid(String staffid)
	{
		int roleid =-1;
		String sql = "select roleid from staff where staffid='"+staffid+"';";
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				roleid=rs.getInt("roleid");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return roleid;
	}
}
