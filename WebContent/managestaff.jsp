<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Staff</title>
<style>
#red{
color:red;
}
#gre{
color:green;
}
</style>
</head>
<body>
	<center>
	<span id="red">${norecord }</span><span id="gre">${updatesuc}</span>
	<span id="gre">${delete }</span><span id="red">${updatefai }</span>
	<span id="gre">${suc}</span>
		<c:url var="addstaff" scope="page" value="addstaff.jsp">
		</c:url>
		<a href="${addstaff}"><h4>Add</h4></a>
		
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Title</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="staff" items="${slist}" >
				<tr>
					<td>
						<c:out value="${staff.staffid}"/>
					</td>
					<td>
						<c:out value="${staff.firstname}"/>
					</td>
					<td>
						<c:out value="${staff.title }"/>
					</td>
					<td><c:url var="edit" scope="page" value="editstaff.jsp">
						<c:param name="staffid" value="${staff.staffid}"></c:param>
						<c:param name="title" value="${staff.title}"></c:param>
						<c:param name="pwd" value="${staff.pwd}"></c:param>
						<c:param name="managerid" value="${staff.managerid}"></c:param>
						</c:url>
						<a href="${edit}"><c:out value="Edit"/></a>
					</td>
					<td><c:url var="del" scope="page" value="/admindelstaff">
						<c:param name="staffid" value="${staff.staffid }"></c:param>
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