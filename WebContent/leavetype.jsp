<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Leave Type</title>
<style>
#p{
color:green;
}
</style>
</head>
<body>
	<center>
		<p id="p">${insert }${delsuccess}</p><span style="color:red">${error}</span>
		<c:url var="addtype" scope="page" value="edittype.jsp">
				<c:param name="leaveid" value=""></c:param>
				<c:param name="leavetype" value=""></c:param>
				<c:param name="limitdays" value=""></c:param>
				<c:param name="createdby" value=""></c:param>
				<c:param name="insert" value="true"/>
		</c:url>
		<a href="${addtype}"><h4>Add</h4></a>
		<br/>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="typename" items="${type}">
				<tr>
					<td>
						<c:out value="${typename.leaveid}"/>
					</td>
					<td>
						<c:out value="${typename.leavetype}"/>
					</td>
					<td><c:url var="viewtype" scope="page" value="edittype.jsp">
						<c:param name="leaveid" value="${typename.leaveid}"></c:param>
						<c:param name="leavetype" value="${typename.leavetype}"></c:param>
						<c:param name="limitdays" value="${typename.limitdays}"></c:param>
						<c:param name="update" value="true"/>
						</c:url>
						<a href="${viewtype}"><c:out value="Edit"/></a>
					</td>
					<td><c:url var="del" scope="page" value="/deletetype">
						<c:param name="typeid" value="${typename.leaveid }"></c:param>
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
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<hr>
		Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved
		</center>
	</center>
</body>
</html>