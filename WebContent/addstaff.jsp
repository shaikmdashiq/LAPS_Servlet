<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Staff</title>
</head>
<body>
	<form action="/LAPS/addstaff" method="post">
	<center>
		<table>
			<tr>
				<td>Staff ID:</td>
				<td><input type="text" size="5" name="staffid"/></td>
				<td>Title:</td>
				<td>
					<select name="title">
						<option value="staff">staff</option>
						<option value="manager">manager</option>
						<option value="admin">admin</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" size="15" name="firstname"/></td>
		
				<td>Last Name:</td>
				<td><input type="text" size="15" name="lastname"/></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><input type="text" size="15" name="gender"/></td>
				
				<td>Password:</td>
				<td><input type="text" size="15" name="password"/></td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><input type="text" size="15" name="email"/></td>
				
				<td>Phone:</td>
				<td><input type="text" size="15" name="phoneno"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><textarea cols="25" rows="5" name="address"></textarea></td>
				<td>Manager Id:</td>
				<td><input type="text" size="15" name="managerid"/></td>
			</tr>
		</table>
		<br/>
		<br/>
		<input type="submit" value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="Reset"/>
		<br/>
		<br/>
		<hr>
		<c:url var = "back" value="/bridge">
		</c:url>
		<a href="${back }">Back To Menu</a>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<hr>
		<p>Copyright © <b>Team 2</b>, 2014-12-10, All Rights Reserved</p>
		</center>
	</center>
	</form>
</body>
</html>