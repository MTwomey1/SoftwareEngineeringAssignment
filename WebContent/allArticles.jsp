<%@page import="assignment.business.Article"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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
</style>
	
	<body>
<table style="width:50%">
<tr>
    <th>First Name</th>
    <th>Surname</th> 
    <th>Username</th>
  </tr>

<% 
	ArrayList<Article> articles = 
		(ArrayList<Article>)request.getSession().getAttribute("articles");

	for(int i = 0; i < articles.size(); i++) {
		%>
		<tr>
			<td><%= articles.get(i).getTitle() %> </td>
			<td><%= articles.get(i).getContents()%></td>
			<td><%= articles.get(i).getDateCreated()%></td>
		</tr>
		<%
		
	}
%>
	
</table>
</body>
</html>