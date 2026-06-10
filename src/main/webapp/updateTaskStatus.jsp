<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Update Task Status</h1>
	
	<form action="updateTaskStatus">
		
		Enter Task Id : <input type="text" name="taskId"><br><br>
		Enter Status : <select name="Status">
		<option value="CREATED">CREATED</option>
		<option value="ASSIGNED">ASSIGNED</option>
		<option value="COMPLETED">COMPLETED</option>
		</select><br><br>
		<input type="submit" value="CHANGE STATUS">
					
	</form>


</body>
</html>