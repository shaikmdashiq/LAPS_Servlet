<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
h4{
color:red;
}
</style>
</head>
<body>
	<center>
	<h4>${record }</h4>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<th>#</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>View Detail</th>
			</tr>
			
			 <c:forEach var="leave" items="${personalform}">
				<tr>
					<td>
						<c:out value="${leave.leaveformid }"/>
					</td>
					<td>
						<c:out value="${leave.startdate}"/>
					</td>
					<td>
						<c:out value="${leave.enddate}"/>
					</td>
					<td>
						<c:url var="personal" scope="page" value="personaldetail.jsp">
						<c:param name="staffid" value="${leave.staffid}"></c:param>
						<c:param name="leavetype" value="${leave.leavetype}"></c:param>
						<c:param name="startdate" value="${leave.startdate}"></c:param>
						<c:param name="enddate" value="${leave.enddate}"></c:param>
						<c:param name="daystaken" value="${leave.daystaken}"></c:param>
						<c:param name="reason" value="${leave.reason}"></c:param>
						<c:param name="workdis" value="${leave.workdissemination}"></c:param>
						<c:param name="contact" value="${leave.contact}"></c:param>
						<c:param name="mcomment" value="${leave.mcomment}"></c:param>
						<c:param name="updatedby" value="${leave.updatedby}"></c:param>
						<c:param name="name" value="${leave.firstname}"></c:param>
					</c:url>
					<a href="${personal}"><c:out value="View Detail"/></a>
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