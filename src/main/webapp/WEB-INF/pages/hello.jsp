<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>

Добро пожаловать в систему удаленного хранения резервных копий
<c:if test="${pageContext.request.userPrincipal.name != null}">
			 , ${pageContext.request.userPrincipal.name}<a
				href="javascript:formSubmit()"> Выйти<br><br></a>
	</c:if>
<a href="/admin/"> Режим администратора.</a>

	<form action="/logout" method="post" id="logoutForm">
		<input type="hidden" 
			name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	
		<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	


</body>
</html>