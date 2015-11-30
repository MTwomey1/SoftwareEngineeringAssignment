<%@page import="assignment.business.Article"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
table, th, td  { 
   border: 1px solid black; 
}
table {
	display: table;
    border-collapse: separate;
    border-spacing: 2px;
    border-color: gray;
}

	<table style="width:50%">
<tr>
    <th>First Name</th>
    <th>Surname</th> 
    <th>Username</th>
    </tr>
</style>

</head>

<body>

<table style="width:50%">
<tr>
    <th>Title</th>
    <th>Content</th> 
    <th>Date</th>
  </tr>
<% 
	Object object = request.getSession().getAttribute("articles");
	Article[] articles = null;
	
	if (object instanceof Article[]) {
		articles = (Article[])object;	
	}
	
	for(int i = 0; i < articles.length; i++) {
		%>
		<tr>
			<td><%= articles[i].getTitle()  %> </td>
			<td><%= articles[i].getContents() %></td>
			<td><%= articles[i].getDateCreated() %></td>
		</tr>
		<%
	}
%>
	
</table>
</body>
</html>