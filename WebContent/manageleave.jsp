<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Leave</title>
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
	<p>${success }</p><span>${norecord }</span>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<th>ID</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Status</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="leaveform" items="${staffform}" >
				<tr>
					<td>
						<c:out value="${leaveform.leaveformid}"/>
					</td>
					<td>
						<c:out value="${leaveform.startdate}"/>
					</td>
					<td>
						<c:out value="${leaveform.enddate}"/>
					</td>
					<td>
						<c:out value="${leaveform.lstatus }"/>
					</td>
					<td><c:url var="viewleave" scope="page" value="editleave.jsp">
						<c:param name="staffid" value="${leaveform.staffid}"></c:param>
						<c:param name="leavetype" value="${leaveform.leavetype}"></c:param>
						<c:param name="startdate" value="${leaveform.startdate}"></c:param>
						<c:param name="fromshift" value="${leaveform.fromshift}"></c:param>
						<c:param name="enddate" value="${leaveform.enddate}"></c:param>
						<c:param name="toshift" value="${leaveform.toshift}"></c:param>
						<c:param name="reason" value="${leaveform.reason}"></c:param>
						<c:param name="workdissemination" value="${leaveform.workdissemination}"></c:param>
						<c:param name="contact" value="${leaveform.contact}"></c:param>
						<c:param name="mcomment" value="${leaveform.mcomment}"></c:param>
						<c:param name="leaveformid" value="${leaveform.leaveformid}"></c:param>
						</c:url>
						<a href="${viewleave}"><c:out value="Edit"/></a>
					</td>
					<td><c:url var="del" scope="page" value="/staffdeleteform">
						<c:param name="leaveformid" value="${leaveform.leaveformid }"></c:param>
						<c:param name="lstatus" value="${leaveform.lstatus }"></c:param>
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