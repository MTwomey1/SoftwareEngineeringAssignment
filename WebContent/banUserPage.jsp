<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="assignment.business.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ban User</title>
<style>
table, th, td {
	border: 1px solid black;
}

table {
	display: table;
	border-collapse: separate;
	border-spacing: 2px;
	border-color: gray;
}
</style>
</head>
<body>

	<form action="FrontController" method="post">
		<table>
			<tr>
				<td>User name :</td>
				<td><input name="usernametoban" size=15 type="text" /></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="BanUser" /> <input
			type="submit" value="Username to Ban" />
	</form>

	<br>
	<br>
	<table style="width: 50%">
		<tr>
			<th>First Name</th>
			<th>Surname</th>
			<th>Username</th>
			<th>Access </th>
		</tr>

		<% 
	
	Object object = (request.getSession().getAttribute("users"));
	User[] users = null;
	
	if (object instanceof User[]) {
		users = (User[])object;
	}
	
	for(int i = 0; i < users.length; i++) {
		%>
		<tr>
			<td><%=users[i].getFirstName() %></td>
			<td><%=users[i].getLastName() %></td>
			<td><%=users[i].getUsername() %></td>
			<td><%=users[i].getAccessPriveledge().toString() %></td>
		</tr>
		<%
		
	}
%>

	</table>
</body>

</html>