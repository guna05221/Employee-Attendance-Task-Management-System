<%@page import="com.eatm.entity.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Task</h2>

	<%
	List<Task> taskList = (List<Task>)request.getAttribute("taskList");
	
	if (taskList != null && !taskList.isEmpty()) {
	%>

	<table border="1">

		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Duration</th>
			<th>Status</th>
		</tr>

		<%
		for (Task task : taskList) {
		%>

		<tr>
			<td><%=task.getTaskId()%></td>
			<td><%=task.getTaskName()%></td>
			<td><%=task.getDuration()%></td>
			<td><%=task.getStatus()%></td>
		</tr>

		<%
		}
		%>

	</table>

	<%
	} else {
	%>

	<h3>No Task Assigned</h3>

	<%
	}
	%>
</body>
</html>