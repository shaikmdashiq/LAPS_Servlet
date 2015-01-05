<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apply for Leave</title>
<style>
span{
color:red;
}
</style>
</head>
<body>
	<center><span>${error}</span>
	<h4>Apply For Leave</h4>
		<form action="/LAPS/getleaveform" method="post">
			<table >
				<tr>	
					<td>Leave Type:</td>
					<td><select name="leavetype">
							<option value="professional_annual">professional_annual</option>
							<option value="admin_annual">admin_annual</option>
							<option value="compensation">compensation</option>
							<option value="medical">medical</option>
						</select>	
					</td>
					<td></td>
				</tr>	
				
				<tr>
					<td>From:</td>
					<td><input type="date" name="startdate"/></td>
					<td><select name="fromshift">
							<option value="am">am</option>
							<option value="pm">pm</option>
						</select>	
					</td>
				</tr>
				
				<tr>
					<td>To:</td>
					<td><input type="date" name="enddate"/></td>
					<td><select name="toshift">
							<option value="am">am</option>
							<option value="pm">pm</option>
						</select>
				</tr>
				
				<tr>
					<td>Reason:</td>
					<td><textarea name="reason" cols="25" rows="5"></textarea></td>
				</tr>
				
				<tr>
					<td>Work Dissemination:</td>
					<td><textarea name="workdis" cols="25" rows="5"></textarea></td>
				</tr>
				
				<tr>
					<td>Contact:</td>
					<td><textarea name="contact" cols="25" rows="5"></textarea></td>
				</tr>
			</table>
			<br/>
			<br/>
			<input type="submit" value="Apply"/>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" value="Reset"/>
		</form>
		
		<br/>
		<br/>
		<hr>
		<c:url var = "back" value="/bridge">
		</c:url>
		<a href="${back }">Back To Menu</a>
	<br/><br/>
		<hr>
		<p>Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved</p>
		</center>
	</center>
</body>
</html>