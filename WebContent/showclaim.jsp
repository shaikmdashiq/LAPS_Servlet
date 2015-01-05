<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show claim</title>
<style>
a{
text-decoration:none;
}
#er{
color:red;
}
#suc{
color:green;
}
p{
color:red;
}
</style>
</head>
<body>
	<center>
		<p>${error }</p><span id="suc">${approve }</span>
		<span id="er">${norec}</span>
		<br/>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Approve/Reject</th>
			</tr>
			<c:forEach var="c" items="${claim}" >
				<tr>
					<td>
						<c:out value="${c.staffid}"/>
					</td>
					<td>
						<c:out value="${c.firstname}"/>
					</td>
			
					<td><c:url var="approve" scope="page" value="/approveclaim">
						<c:param name="cid" value="${c.id}"></c:param>
						<c:param name="sid" value="${c.staffid }"></c:param>
						</c:url>
						<a href="${approve}">Approve</a>
					&nbsp;/&nbsp;
					<c:url var="reject" scope="page" value="/rejectclaim">
						<c:param name="cid" value="${c.id }"></c:param>
						</c:url>
						<a href="${reject}">Reject</a>
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