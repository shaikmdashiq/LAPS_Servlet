<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit leave type</title>
</head>
<body>
	<form action="/LAPS/updatetype" method="post">
	<center>
		<table cellpadding=6 cellspacing=4 border=0>
			<tr>
				<td>ID:</td>
				<td><c:if test="${param['insert']==true}">
					<input type="text" name="addid" value=""/>
					<input type="hidden" name="ins" value="t"/>
					</c:if>
					<c:if test="${param['update']==true}">
					${param['leaveid']}
					<input type="hidden" name="updateid" value="${param['leaveid']}"/>
					<input type="hidden" name="ins" value="f"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>Type:</td>
				<td><input type="text" name="leavetype" value="${param['leavetype'] }"></td>
			</tr>
			<tr>
				<td>Entitlement:</td>
				<td><input type="text" name="limitdays" value="${param['limitdays'] }"></td>
			</tr>
		</table>
		<p style="color:red">${dayserror}</p>
		<br/>
		<br/>
		<input type="submit" value="Submit"/>
		<input type="reset" value="Reset"/>
		<br/>
		<br/>
		<hr>
		<c:url var = "back" value="/bridge">
		</c:url>
		<a href="${back }">Back To Menu</a>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<hr>
		<p>Copyright © <b>Team 2</b>, 2014-12-10, All Right Reserved</p>
		</center>
	</center>
	</form>
</body>
</html>