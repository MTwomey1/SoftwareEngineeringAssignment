<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	You have been logged in successfully.

	<br />
	<br />

	<c:set var="user" value="${sessionScope.user}" />
	<b>Welcome<c:out value="${user.firstName}" /></b>
	<b>What would like to do?</b>

	<br />
	<br />

	<form action="FrontController" method="post">
		<input type="hidden" name="action" value="ViewUserProfile" /> <input
			type="submit" value="View My Profile" />
	</form>

	<form action="FrontController" method="post">
		<input type="hidden" name="action" value="NewsFeed" /> <input
			type="submit" value="News Feed" />
	</form>

	<form action="FrontController" method="post">
		<input type="hidden" name="action" value="ShowAllUsers" /> <input
			type="submit" value="Ban User" />
	</form>
</body>
</html>