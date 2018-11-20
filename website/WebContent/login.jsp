<%@
 	page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<form style="text-align: center" action="hello" method="post">
		userName: <input type = "text" name="username" value="" />
		<br>
		password: <input type="password" name="password" value=""/>
		<br>
		<input type = "submit" value="ok man" >
	</form>
	<form style="text-align: center" action="testget" method="get">
		userName: <input type = "text" name="username" value="" />
		<br>
		password: <input type="password" name="password" value=""/>
		<br>
		<input type = "submit" value="ok man" >
	</form>
	
</body>
</html>