<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<jsp:include page="header.jsp" />
<head>
	<title>Home</title>
	<spring:url value="/sessionController/login" var="url_LoginForm"/>
</head>
<body>
<h1>
	Bienvenido a la aplicación WolfMaths 
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p> Por favor, logueese para poder empezar a usar la aplicación</p>
<form:form id="homePage" name="homePage" modelAttribute="sessionForm" action="${url_LoginForm}" method="post">
<form:input id="userNameLogin" name="userNameLogin" path="userNameLogin"/>
<form:password id="passwordLogin" name="passwordLogin" path="passwordLogin" />
<form:button type="submit">Validar</form:button>
</form:form>
</body>
</html>
