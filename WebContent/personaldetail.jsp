<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal Leave Detail</title>
</head>
<body>
	<center>
	<h4>Personal Leave Detail</h4><br/>
		<table cellpadding=6 cellspacing=5 border=1>
			<tr>
				<td>Staff ID:</td>
				<td>${param['staffid']}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>${param['name']}</td>
			</tr>
			<tr>
				<td>Leave Type:</td>
				<td>${param['leavetype']}</td>
			</tr>
			<tr>
				<td>Start Date:</td>
				<td>${param['startdate']}</td>
			</tr>
			<tr>
				<td>End Date:</td>
				<td>${param['enddate']}</td>
			</tr>
			<tr>
				<td>Days Taken:</td>
				<td>${param['daystaken'] }</td>
			</tr>
			<tr>
				<td>Updated By:</td>
				<td>${param['updatedby'] }</td>
			</tr>
			<tr>
				<td>Reason:</td>
				<td>${param['reason']}</td>
			</tr>
			<tr>
				<td>Work Dissemination:</td>
				<td>${param['workdis']}</td>
			</tr>
			<tr>
				<td>Contact:</td>
				<td>${param['contact']}</td>
			</tr>
			<tr>
				<td>Comment:</td>
				<td>${param['mcomment']}</td>
			</tr>
		</table>
		<br/>
		<br/>
		<hr>
		<c:url var = "back" value="/bridge">
		</c:url>
		<a href="${back }">Back To Menu</a>
		<br/><br/>
		<hr>
		Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved
		</center>
	</center>
</body>
</html>