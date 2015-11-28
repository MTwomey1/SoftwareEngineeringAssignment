<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create your article</title>
</head>

<body>

	<form action="FrontController" method="post">
		<table>
			<tr>
				<td>Title :</td>
				<td><input name="title" size=15 type="text" /></td>
			</tr>
			<tr>
				<td>Content :</td>
				<td>
					<textarea name = "contentInputArea" rows="4" cols="50"></textarea>
				</td>
			</tr>
		</table>
		<input type="hidden" name="action" value="InsertArticle" /> 
		<input type="submit" value="submit" />
	</form>

</body>
</html>