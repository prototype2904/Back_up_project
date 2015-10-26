<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>It's an admin page</title>
</head>
<body>

	<table>
	<tr>
		<th align="left" width="10%"> ID </th>
		<th align="left" width="10%"> User</th>
		<th align="left" width="20%"> Path </th>
		<th align="left" width="10%"> Filename</th>
		<th align="left" width="10%"> Format</th>
	</tr>
		<c:forEach var="file" items="${files}">
		<tr>
			<td>${file.taskFromServer.id}</td>
			<td>${file.taskFromServer.user.username}</td>
			<td>${file.taskFromServer.dirPath}</td>
			<td>${file.taskFromServer.filename}</td>
			<td>${file.taskFromServer.format}</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>