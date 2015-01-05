package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.LeaveForm;
import model.LeaveType;
import model.Staff;

public class LeaveFormSql {
	
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
	
	public ArrayList<LeaveForm> getAppliedLeave(String logid)
	{
		ArrayList<LeaveForm> leave =null; 
				leave =new ArrayList<LeaveForm>();
		String sql = "select * from LeaveForm where (lstatus=\"Applied\" or lstatus=\"Updated\") and staffid in (select staffid from Staff where managerid=\""+logid+"\")";
		LeaveTypeSql lt = new LeaveTypeSql();
		StaffSql ss = new StaffSql();
		
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				LeaveForm lf = new LeaveForm();
				lf.setFirstname((ss.getStaffById(rs.getString("staffid")).getFirstname()));
				lf.setLeavetype(lt.getLeaveTypeById(rs.getInt("leaveid")));
				lf.setStaffid(rs.getString("staffid"));
				lf.setStartdate(rs.getDate("startdate").toString());
				lf.setEnddate(rs.getDate("enddate").toString());
				lf.setDaystaken(rs.getFloat("daystaken"));
				lf.setReason(rs.getString("reason"));
				lf.setLeaveformid(rs.getInt("leaveformid"));
				leave.add(lf);
			}
			
			sm.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return leave;
	}
	
	//get subordinates' leave history!!!
	public ArrayList<LeaveForm> getStaffLeave(String logid)
	{
		ArrayList<LeaveForm> leave = new ArrayList<LeaveForm>();
		String sql = "select * from LeaveForm where lstatus=\"Approved\" and staffid in (select staffid from Staff where managerid=\""+logid+"\")";
		LeaveTypeSql lt = new LeaveTypeSql();
		StaffSql ss = new StaffSql();
		
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				LeaveForm lf = new LeaveForm();
				lf.setLeavetype(lt.getLeaveTypeById(rs.getInt("leaveid")));
				lf.setFirstname((ss.getStaffById(rs.getString("staffid")).getFirstname()));
				lf.setLeavetype(lt.getLeaveTypeById(rs.getInt("leaveid")));
				lf.setStaffid(rs.getString("staffid"));
				lf.setStartdate(rs.getDate("startdate").toString());
				lf.setEnddate(rs.getDate("enddate").toString());
				lf.setDaystaken(rs.getFloat("daystaken"));
				lf.setReason(rs.getString("reason"));
				lf.setLeaveformid(rs.getInt("leaveformid"));
				leave.add(lf);
			}
			
			sm.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return leave;
	}
	
	//update on "approve/reject" process!!!
	public void updateStatus(String status,String comment,int lfid)
	{
		String sql = "update leaveform set lstatus='"+status+"',  mcomment='"+comment+"' where leaveformid="+lfid+";";
		Connection conn = null;
		try
		{
			conn = getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	//get staff leave to manage his leave
	public ArrayList<LeaveForm> manageSelfLeave(String logid)
	{
		ArrayList<LeaveForm> leave =null; 
			leave=	new ArrayList<LeaveForm>();
		String sql = "select * from LeaveForm where (lstatus='Approved' or lstatus='Applied' or lstatus='updated') and staffid='"+logid+"';";
		LeaveTypeSql lt = new LeaveTypeSql();
		StaffSql ss = new StaffSql();
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				LeaveForm lf = new LeaveForm();
				lf.setLeavetype(lt.getLeaveTypeById(rs.getInt("leaveid")));
				lf.setFirstname((ss.getStaffById(rs.getString("staffid")).getFirstname()));
				lf.setStaffid(logid);
				lf.setStartdate(rs.getString("startdate"));
				lf.setEnddate(rs.getString("enddate").toString());
				lf.setDaystaken(rs.getFloat("daystaken"));
				lf.setReason(rs.getString("reason"));
				lf.setWorkdissemination(rs.getString("workdissemination"));
				lf.setContact(rs.getString("contact"));
				lf.setLeaveformid(rs.getInt("leaveformid"));
				lf.setFromshift(rs.getString("fromshift"));
				lf.setToshift(rs.getString("toshift"));
				lf.setMcomment(rs.getString("mcomment"));
				lf.setLstatus(rs.getString("lstatus"));
				leave.add(lf);
			}
			
			sm.close();
			conn.close();
			return leave;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return leave;
	}
	
	//delete form according to leaveformid
	public void deleteLeaveForm(LeaveForm lf)
	{
		if("Approved".equals(lf.getLstatus()))
			{
				lf.setLstatus("Cancelled");
			}else
			{
				lf.setLstatus("Deleted");
			}
		String sql = "update LeaveForm set lstatus='"+lf.getLstatus()+"' where leaveformid="+lf.getLeaveformid()+";";
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
	
	//update leave form
	public void updateLeaveForm(LeaveForm lf)throws Exception
	{
		 
			String start1 = lf.getStartdate();
			String start = lf.getStartdate().substring(8);
			String s2 = lf.getStartdate().substring(5,7);
			String e2 = lf.getEnddate().substring(5,7);
			String end = lf.getEnddate().substring(8);
			try{
			float istart = Float.parseFloat(start.replaceFirst("^0+(?!$)", ""));
			float iend =Float.parseFloat(end.replaceFirst("^0+(?!$)", ""));
			float is2 = Float.parseFloat(s2.replaceFirst("^0+(?!$)", ""));
			float ie2 =Float.parseFloat(e2.replaceFirst("^0+(?!$)", ""));
			if(is2==ie2)
			{
				if(lf.getFromshift().equals(lf.getToshift()))
					{
						lf.setDaystaken(iend-istart+0.5f);
					}else
						{
							lf.setDaystaken(iend-istart);
						}
				if("compensation".equals(lf.getLeavetype()))
					{
						lf.setDaystaken(0.5f);
						start1=lf.getEnddate();
					}
			}else
			{
				if(lf.getFromshift().equals(lf.getToshift()))
				{
					lf.setDaystaken(iend+(31-istart)+0.5f);
				}else
					{
						lf.setDaystaken(iend+(31-istart));
					}
			if("compensation".equals(lf.getLeavetype()))
				{
					lf.setDaystaken(0.5f);
					start1=lf.getEnddate();
				}
			}
			}catch(Exception e)
			{
				throw new Exception("Check your input !");
			}
			//check whether it's the same month;
			
			//check compensation
			StaffSql  ss = new StaffSql();
			int com = ss.getCompensation(lf.getStaffid());
			if("compensation".equals(lf.getLeavetype())&&com<1)
				{
					throw new Exception("No enough compensation !");
				}else{
					
					ss.updateCompensationone(lf.getStaffid());
				}

		
		//update command
		String sql = "update leaveform set leaveid=\""+lf.getLeaveid()+"\",startdate='"+lf.getStartdate()
				+"',fromshift='"+lf.getFromshift()+"',enddate='"+lf.getEnddate()+"',toshift='"+lf.getToshift()
				+"',reason='"+lf.getReason()+"',daystaken="+lf.getDaystaken()+",lstatus='Updated',reason='"
				+lf.getReason()+"',workdissemination='"+lf.getWorkdissemination()+"',contact='"+lf.getContact()
				+"',updatedby='"+lf.getStaffid()+"'"
				+" where leaveformid="+lf.getLeaveformid()+";";
		
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
	
	//view personal leave history
	public ArrayList<LeaveForm> getPersonalHistory(String logid)
	{
		ArrayList<LeaveForm> leave =null; 
			leave=	new ArrayList<LeaveForm>();
		String sql = "select * from LeaveForm where staffid='"+logid+"';";
		LeaveTypeSql lt = new LeaveTypeSql();
		StaffSql ss = new StaffSql();
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				LeaveForm lf = new LeaveForm();
				lf.setLeavetype(lt.getLeaveTypeById(rs.getInt("leaveid")));
				lf.setFirstname((ss.getStaffById(rs.getString("staffid")).getFirstname()));
				lf.setStaffid(logid);
				lf.setStartdate(rs.getString("startdate"));
				lf.setEnddate(rs.getString("enddate"));
				lf.setDaystaken(rs.getFloat("daystaken"));
				lf.setReason(rs.getString("reason"));
				lf.setWorkdissemination(rs.getString("workdissemination"));
				lf.setContact(rs.getString("contact"));
				lf.setLeaveformid(rs.getInt("leaveformid"));
				lf.setFromshift(rs.getString("fromshift"));
				lf.setToshift(rs.getString("toshift"));
				lf.setMcomment(rs.getString("mcomment"));
				lf.setLstatus(rs.getString("lstatus"));
				lf.setUpdatedby(rs.getString("updatedby"));
				leave.add(lf);
			}
			
			sm.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return leave;
	}
	
	public Float getStaffAnnualDaysTaken(LeaveForm lf)
	{
		
		String sql = "select daystaken from leaveform where staffid='"+lf.getStaffid()+"' and leaveid=2;";
		float sum = 0;
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				sum+=rs.getFloat("daystaken");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sum;
	}
	
	public Float getManagerAnnualDaysTaken(LeaveForm lf)
	{
		
		String sql = "select daystaken from leaveform where staffid='"+lf.getStaffid()+"' and leaveid=3;";
		float sum = 0;
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				sum+=rs.getFloat("daystaken");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sum;
	}
	
	public Float getMedicalDaysTaken(LeaveForm lf)
	{
		
		String sql = "select daystaken from leaveform where staffid='"+lf.getStaffid()+"' and leaveid=4;";
		float sum = 0;
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			while(rs.next())
			{
				sum+=rs.getFloat("daystaken");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sum;
	}
	//add new leaveform
	
	public void insertLeaveForm(LeaveForm lf)throws Exception
	{
		
			String startday = lf.getStartdate();
			String endday = lf.getEnddate();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			Date sd ,ed;
			sd = df.parse(startday);
			ed = df.parse(endday);
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			start.setTime(sd); end.setTime(ed);
			int daystaken = 0;
			int dif=0;
			 while(start.compareTo(end)<0){

				 start.add(Calendar.DAY_OF_YEAR, 1);
				 dif++;
			    if(start.getTime().getDay()-1 != Calendar.SUNDAY && start.getTime().getDay()-1 !=Calendar.SATURDAY){

			    		daystaken++;
				   }
				 }
			 
			 if("compensation".equals(lf.getLeavetype()))
				{
						lf.setDaystaken(0.5f);
						lf.setEnddate(startday);
				}else
				{
					if(lf.getFromshift().equals(lf.getToshift()))
					{
						if(dif<=14){
							lf.setDaystaken(daystaken+0.5f);
						}else{
							lf.setDaystaken(dif+0.5F);
						}
					}else{
						if(dif<=14){
							lf.setDaystaken(daystaken);
						}else{
							lf.setDaystaken(dif);
						}
						
					}
				}
			
			if(null==startday||null==endday)
			{
				throw new Exception("Date can't be null !");
			}
			
			StaffSql  ss = new StaffSql();
			int com = ss.getCompensation(lf.getStaffid());
			if("compensation".equals(lf.getLeavetype())&&com<1)
				{
					throw new Exception("No enough compensation !");
				}else{
					
					ss.updateCompensationone(lf.getStaffid());
				}
			//check whether it's the same month;
		
		float staffdaystaken = getStaffAnnualDaysTaken(lf);
		float managerdaystaken = getManagerAnnualDaysTaken(lf);
		float medicaldays = getMedicalDaysTaken(lf);
		StaffSql staff = new StaffSql();
		int roleid = staff.getRoleIdByStaffid(lf.getStaffid());
		RoleSql rss = new RoleSql();
		String title = rss.getTitleById(roleid);
		if("staff".equals(title)&&"professional_annual".equals(lf.getLeavetype()))
		{
			if(18-staffdaystaken<lf.getDaystaken())
			{
				throw new Exception("You don't have enough annual leave day!");
			}
		}
		if("manager".equals(title)&&"admin_annual".equals(lf.getLeavetype()))
		{
			if(14-managerdaystaken<0)
			{
				throw new Exception("You don't have enough annual leave day!");
			}
		}
		if("midecal".equals(lf.getLeavetype()))
		{
			if(60-medicaldays<0||dif>60)
			{
				throw new Exception("You don't have enough medical leave day!");
			}
		}
		//insert begin!!
		String sql = "insert into LeaveForm (leaveid,staffid,startdate,fromshift,enddate,toshift,daystaken"
				+",lstatus,reason,workdissemination,contact) values("+lf.getLeaveid()+",'"+lf.getStaffid()
				+"','"+lf.getStartdate()+"','"+lf.getFromshift()+"','"+lf.getEnddate()+"','"+lf.getToshift()
				+"',"+lf.getDaystaken()+",'Applied','"+lf.getReason()+"','"+lf.getWorkdissemination()+"','"
				+lf.getContact()+"');";
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
			String error = "Error on insert new form: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
