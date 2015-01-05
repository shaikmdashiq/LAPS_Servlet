<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LAPS Log In System</title>
<style>
p{
color:red;
}
</style>
</head>
<body>
	<center>
		<h3>LAPS &nbsp;Log&nbsp; In&nbsp; System</h3>
		<hr/>
		<br/><br/>
		<form action="/LAPS/login" method="post">
			<table>
				<tr>
					<td>ID:</td>
					<td><input type="text" size=15 name="id"/></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" size=15 name="pwd"/></td>
				</tr>
			</table>
			<br/>
			<input type="submit" value="Log In"/>
		</form>
		<p>${error}</p>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<hr>
		Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved
		</center>
	</center>
</body>
</html>