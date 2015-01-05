<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in menu</title>
<style>
#suc{
color:green;
}
h4,#err{
color:red;
}
#name{
font-family:cursive;
font-size:1.3em;
color:;
}
a{
text-decoration:none;
background:#F8F8F8 ;
}
a:hover{
font-size:1.2em;
}
div{
height:25px;
width:250px;
background-color:#D8D8D8 ;
text-align:center;
padding-top:5px;
}
h3{
text-decoration: underline;
}
</style>
</head>
<body><center>
<h4>${error}</h4>
	
		<span id="suc">${formsuc}</span>
		<span id="err">${formerror}</span>
		<span id="suc">${claimsuc }</span><span id="err">${claimfai}</span>
		<br/><br/>
		<h3>Welcome &nbsp;<span id="name">${fname}</span></h3>
		<br/><br/><br/>
		<c:set var="name" value="${path}"/>
	
		<span></span>
		<!-- staff login menu -->
		<c:if test="${name==\"staff\"}">
		
			<table>
				<tr>
					<td><a href="leaveform.jsp"><div>Apply Leave</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/manageleave"><div>Manage Self Leave</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/personalhistory"><div>View Personal Leave History</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/addclaim"><div>Claim Compensation</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="login.jsp"><div>Log out</div></a></td>
				</tr>
			</table>
		</c:if>
		
		<!-- manager login menu -->
		<c:if test="${name==\"manager\"}">
			<table>
				<tr>
					<td><a href="leaveform.jsp"><div>Apply Leave</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/manageleave"><div>Manage Self Leave</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/personalhistory"><div>View Personal Leave History</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/viewsubleave"><div>Subordinate Leave History</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/approveleave"><div>Approve/Reject Leave</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/showclaim"><div>Approve/Reject Compensation</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="login.jsp"><div>Log out</div></a></td>
				</tr>
			</table>
			
		</c:if>
		
		<!-- admin login menu -->
		<c:if test="${name==\"admin\" }">
			<table>
				<tr>
					<td><a href="/LAPS/managestaff"><div>Manage Staff</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/leavetype"><div>Manage Leave Type</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/manageholiday"><div>Manage Holiday</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/leavetype"><div>Manage Leave Entitlement</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="/LAPS/managestaff"><div>Manage Approval Hierarchy</div></a></td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><a href="login.jsp"><div>Log out</div></a></td>
				</tr>
			</table>
		</c:if>
		<br/><br/><br/><br/><br/><br/><br/><br/>
		<hr>
		Copyright © <b>Team 2</b>, 2014-12-10, All Rights Reserved
		</center>
</body>
</html>