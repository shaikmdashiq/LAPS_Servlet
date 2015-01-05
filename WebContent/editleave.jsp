<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Your Leave</title>
</head>
<body>
	<form action="/LAPS/updatestaffleave" method="post">
	<center>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<td>Staff ID:</td>
				<td>${param['staffid']}</td>
			</tr>
			<tr>
				<td>Leave Type:</td>
				<td><c:if test="${param['leavetype']==\"compensation\"}">
				<select name="type">
					<option value="compensation">compensation</option>
					<option value="professional_annual">professional_annual</option>
					<option value="admin_annual">admin_annual</option>
					<option value="medical">medical</option>
				</select>
				</c:if>
				<c:if test="${param['leavetype']==\"professional_annual\"}">
				<select name="type">
					<option value="professional_annual">professional_annual</option>
					<option value="admin_annual">admin_annual</option>
					<option value="compensation">compensation</option>
					<option value="medical">medical</option>
				</select>
				</c:if>
				<c:if test="${param['leavetype']==\"admin_annual\"}">
				<select name="type">
					<option value="admin_annual">admin_annual</option>
					<option value="compensation">compensation</option>
					<option value="professional_annual">professional_annual</option>
					<option value="medical">medical</option>
				</select>
				</c:if>
				<c:if test="${param['leavetype']==\"medical\"}">
				<select name="type">
					<option value="compensation">compensation</option>
					<option value="admin_annual">admin_annual</option>
					<option value="professional_annual">professional_annual</option>
					<option value="medical">medical</option>
				</select>
				</c:if>
				</td>
			</tr>
			<tr>
				<td>From:</td>
				<td><input name="startdate" type="date" value="${param['startdate']}"/></td>
				<td>
					<select name="time">
							<option value="am">am</option>
							<option value="pm">pm</option>
						</select>
				</td>
			</tr>
			<tr>
				<td>To:</td>
				<td><input name="enddate" type="date" value="${param['enddate']}"/></td>
				<td>
					<select name="ampm">
							<option value="am">am</option>
							<option value="pm">pm</option>
						</select>
				</td>
			</tr>
			<tr>
				<td>Reason:</td>
				<td>
					<textarea name="reason" cols="25" rows="5">${param['reason'] }</textarea>
				</td>
			</tr>
			<tr>
				<td>Work Dissemination:</td>
				<td><textarea name="workdis" cols="25" rows="5">${param['workdissemination'] }</textarea></td>
			</tr>
			<tr>
				<td>Contact:</td>
				<td><textarea name="contact" cols="25" rows="5">${param['contact'] }</textarea></td>
			</tr>
			<tr>
				<td>Comment:</td>
				<td><textarea name="comment" cols="25" rows="5" readonly="readonly">${param['mcomment']}</textarea></td>
			</tr>
		</table>
		<input type="hidden"  name="leaveformid" value="${param['leaveformid'] }"/>
		<input type="submit" value="Update"/>
		<input type="reset" value="Reset"/>
		<br/>
		<br/>
		<hr>
		<c:url var = "back" value="/bridge">
		</c:url>
		<a href="${back }">Back To Menu</a>
		<br/><br/><br/><br/>
		<hr>
		Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved
		</center>
	</center>
	</form>
</body>
</html>