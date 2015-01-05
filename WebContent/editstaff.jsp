<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/LAPS/updatestaff" method="post">
	<center>
		<table>
			<tr>
				<td>Staff ID:</td>
				<td><input type="hidden" name="staffid" value="${param['staffid']}"/>
				${param['staffid'] }
				</td>
			</tr>
			<tr>
				<td>Title</td>
				<td><c:if test="${param['title']==\"admin\"}">
					<select name="type">
					<option value="admin">admin</option>
						<option value="manager">manager</option>
						<option value="staff">staff</option>
					</select>
					</c:if>
					<c:if test="${param['title']==\"manager\"}">
					<select name="type">
						<option value="manager">manager</option>
						<option value="admin">admin</option>
						<option value="staff">staff</option>
					</select>
					</c:if>
					<c:if test="${param['title']==\"staff\"}">
					<select name="type">
						<option value="staff">staff</option>
						<option value="admin">admin</option>
						<option value="manager">manager</option>
					</select>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="password" type="text" value="${param['pwd'] }"/></td>
			</tr>
			<tr>
				<td>Manager ID:</td>
				<td><input type="text" name="managerid" value="${param['managerid'] }" placeholder="if any"></td>
			</tr>
		</table>
		<br/>
		<br/>
		<input type="submit" value="Update"/>
		<input type="reset" value="Reset"/>
		<br/>
		<br/>
		<hr>
		<c:url var = "back" value="/bridge">
		</c:url>
		<a href="${back }">Back To Menu</a>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<hr>
		<p>Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved</p>
		</center>
	</center>
</form>
</body>
</html>