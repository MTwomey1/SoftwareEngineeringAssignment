<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="assignment.business.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="FrontController" method="post">
		<table>
			<tr>
				<td>User name :</td>
				<td><input name="username" size=15 type="text" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input name="password" size=15 type="password" /></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="LoginUser" /> <input
			type="submit" value="login" />
	</form>
</body>
</html>