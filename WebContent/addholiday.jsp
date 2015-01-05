<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Holiday</title>
</head>
<body>
<form action="/LAPS/addholiday" method="post">
	<center>
		<h3>Add Holiday</h3><br/>
		<table>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="hid"/></td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><input type="text" name="hdate"/></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="description"/>
			</tr>
		</table>
		<br/>
		<br/>
		<input type="submit" value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="Reset"/>
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