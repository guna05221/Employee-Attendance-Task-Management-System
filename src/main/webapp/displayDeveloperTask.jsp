<%@page import="com.eatm.entity.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.eatm.entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	Employee employee = (Employee) request.getAttribute("employee");
	if (employee != null) {
	%>
	<h2>Employee Details</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<td><%=employee.getEmployeeId()%></td>
		</tr>
		<tr>
			<th>Name</th>
			<td><%=employee.getEmployeeName()%></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><%=employee.getEmail()%></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><%=employee.getPassword()%></td>
		</tr>
		<tr>
			<th>Phone Number</th>
			<td><%=employee.getPhoneNumber()%></td>
		</tr>
		<tr>
			<th>Salary</th>
			<td><%=employee.getSalary()%></td>
		</tr>
	</table>


	<h2>Task</h2>
	<%
	List<Task> taskList = employee.getTaskList();
	if (taskList != null && !taskList.isEmpty()) {
	%>
	
	<table border="1">
		<%
		for (Task task : taskList) {
		%>
		<tr>
			<th>Task ID</th>
			<th>Task Name</th>
			<th>Duration</th>
			<th>Status</th>
		</tr>
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
	<h2>No task assigned</h2>
	<%
	}
	} else {
	%>
	<h3>No Employee Found</h3>
	<%
	}
	%>

</body>
</html>