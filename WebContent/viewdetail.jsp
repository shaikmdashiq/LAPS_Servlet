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
	<center>
	<h4>Leave Application Detail</h4><br/>
	<p>${result }</p>
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
				<td>Reason:</td>
				<td>${param['reason']}</td>
			</tr>
		</table>
		<form name="myform" action="/LAPS/approveorreject" method="post">
			<textarea name="comment" placeholder="Leave a comment here" cols="28" rows="8" ></textarea>
			<br/>
			<input type="hidden" name="id" value="${param['lfid'] }"/>
			<input type="hidden" name="Approved"/>
			<input type="hidden" name="Rejected"/>
			<input name="btn" type="submit" value="Approve" onclick="{document.myform.Approved.value=this.value;}"/>&nbsp;&nbsp;&nbsp;
			<input name="btn" type ="submit" value="Reject" onclick="{document.myform.Rejected.value=this.value;}"/>
		</form>
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