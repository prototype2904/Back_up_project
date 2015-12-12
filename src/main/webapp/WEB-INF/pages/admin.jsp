<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>It's an admin page</title>

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body>
<h5 align="right"><a href="/">Нажмите, чтобы вернуться на главную страницу.</a></h5>
<h1 align="center">Режим Администратора</h1>



<h2>Добавление новой задачи</h2>
<div style="border-radius: 5px; border: 2px solid blue; width: 27%">
			<table style="padding: 5px 5px 5px 5px">
	<form:form action="/admin/tasks/add/" modelAttribute="taskForServer"  method="POST" >

			<tr>
				<td><form:label path="user.id">Пользователь</form:label></td>
				<td><form:select path="user.id">
						<form:options itemLabel="username" itemValue="id" items="${users}"></form:options>
				</form:select></td>
				<td><form:errors path="user" cssClass="error"></form:errors></td>
				
			</tr>
			<tr>
				<td><form:label path="dirPath">Путь в директории до файла</form:label></td>
				<td><form:input type="text" path="dirPath"/></td>
				<td><form:errors path="dirPath" cssClass="error"></form:errors></td>
			</tr>
			
			<tr>
				<td><form:label path="filename">Название файла</form:label></td>
				<td><form:input type="text" path="filename"/></td>
				<td><form:errors path="filename" cssClass="error"></form:errors></td>
			</tr>
			
			<tr>
				<td><form:label path="format">Формат файла</form:label></td>
				<td><form:input type="text" path="format"/></td>
				<td><form:errors path="format" cssClass="error"></form:errors></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Добавить" /></td>
				<td><input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /></td>
			</tr>

	</form:form>
		</table>
</div>	
	
	<h1>Задачи на сервере.</h1>
	<c:if test="${tasks.size() > 0}">
	<table>
	<tr>
		<th align="left" width="10%"> Идентификатор </th>
		<th align="left" width="10%"> Пользователь</th>
		<th align="left" width="20%"> Путь до файла </th>
		<th align="left" width="10%"> Название файла</th>
		<th align="left" width="10%"> Формат</th>
		<th align="left" width="5%"></th>
	</tr>
		<c:forEach var="task" items="${tasks}">
		<tr>
			<td>${task.taskFromServer.id}</td>
			<td><a href="/admin/tasks/${task.taskFromServer.user.id}"> ${task.taskFromServer.user.username}</a></td>
			<td>${task.taskFromServer.dirPath}</td>
			<td><a href="/admin/tasks/${task.taskFromServer.filename}/${task.taskFromServer.format}/${task.taskFromServer.user.id}">${task.taskFromServer.filename}</a></td>
			<td>${task.taskFromServer.format}</td>
			<td><a href="/admin/tasks/delete/${task.taskFromServer.id}">Delete</a></td>
		</tr>
		</c:forEach>
	</table>
	
	</c:if>
	<c:if test="${tasks.size() == 0}">
		Задач пока нет
	</c:if>
	
	<h1>Резервные копии, хранящиеся на сервере</h1>
	
	<c:if test="${files.size() > 0}">
	<table>
		<tr>
		<th align="left" width="10%"> Идентификатор </th>
		<th align="left" width="10%"> Пользователь</th>
		<th align="left" width="20%"> Название файла </th>
		<th align="left" width="10%"> Формат</th>
		<th align="left" width="10%"> Версия</th>
		<th align="left" width="10%"> Хэш файла</th>
		<th align="left" width="5%"></th>
	</tr>
		<c:forEach var="file" items="${files}">
		<tr>
			<td>${file.id}</td>
			<td>${file.user.username}</td>
			<td>${file.filename}</td>
			<td>${file.format}</td>
			<td>${file.version}</td>
			<td>${file.checksum}</td>
			<td><a href="/admin/files/delete/${file.id}">Delete</a></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${files.size() == 0}">
		Файлов пока нет
	</c:if>
	

</body>
</html>