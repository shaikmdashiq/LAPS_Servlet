<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Holiday</title>
<style>
p{
color:green;
}
span{
color:red;
}
</style>
</head>
<body>
	<center>
	<p>${addsuccess}${del}</p><span>${norec}</span>
		<c:url var="addholiday" scope="page" value="addholiday.jsp">
		</c:url>
		<a href="${addholiday}">
		<h4>Add</h4>
		</a>
		<br/>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<th>ID</th>
				<th>Date</th>
				<th>Name</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="ho" items="${holiday}" >
				<tr>
					<td>
						<c:out value="${ho.hid}"/>
					</td>
					<td>
						<c:out value="${ho.hdate}"/>
					</td>
					<td>
						<c:out value="${ho.description}"/>
					</td>
					<td><c:url var="del" scope="page" value="/admindelholiday">
						<c:param name="hid" value="${ho.hid }"></c:param>
						</c:url>
						<a href="${del}" >Delete</a>
					</td>
				</tr>
			</c:forEach>
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