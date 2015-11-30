<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="FrontController" method="post">
		<table>
			<tr>
				<td>User name :</td>
				<td><input name="User name" size=15 type="text" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input name="Password" size=15 type="password" /></td>
			</tr>
			<tr>
				<td>First name</td>
				<td><input name="First Name" size=15 type="text" /></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><input name="Last Name" size=15 type="text" /></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="CreateUser" /> <input
			type="submit" value="login" />
	</form>
</body>
</html>