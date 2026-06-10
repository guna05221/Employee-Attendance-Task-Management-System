<%@page import="java.util.List"%>
<%@page import="com.eatm.entity.Attendance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Attendance</h1>

	<%
	List<Attendance> attendanceList = (List<Attendance>) request.getAttribute("list");

	if (attendanceList != null) {
	%>

	<table border="1">

		<tr>
			<th>Login Time</th>
			<th>Logout Time</th>
		</tr>

		<%
		for (Attendance list : attendanceList) {
		%>

		<tr>
			<td><%=list.getLoginTime()%></td>
			<td><%=list.getLogoutTime()%></td>
		</tr>

		<%
		}
		%>

	</table>

	<%
	} else {
	%>

	<h2>No Attendance Found</h2>

	<%
	}
	%>


</body>
</html>