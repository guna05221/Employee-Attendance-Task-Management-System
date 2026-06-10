<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Login page</h1>
	<form action="login">
		Enter Email : <input type="text" name="loginEmail"><br>
		Enter Password : <input type="text" name="loginPassword"><br>
		<input type="submit" value="Login">
		<h2>
			<a href="takeEmail.jsp">ForgotPassword?</a> <a href="register.jsp">NewUser?</a>
		</h2>
</body>
</html>