package model;


public class LeaveType {
	private int leaveid;
	private String leavetype;
	private Float limitdays;
	private String createdby;
	private String updatedby;
	
	
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public int getLeaveid() {
		return leaveid;
	}
	public void setLeaveid(int leaveid) {
		this.leaveid = leaveid;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public Float getLimitdays() {
		return limitdays;
	}
	public void setLimitdays(Float limitdays) {
		this.limitdays = limitdays;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	
}
