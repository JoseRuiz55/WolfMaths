<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<jsp:include page="/WEB-INF/views/header.jsp" />
<head>
<spring:url value="/problem/createProblemForm" var="url_newProblemForm"/>
<spring:url value="/problem/resolveProblemForm" var="url_resolveProblemForm"/>
<spring:url value="/alumno/obtenerTareasAlumno" var="url_getTareasAlumno"/>
<spring:url value="/profesor/obtenerProblemasProfesor" var="url_getProblemasProfesor"/>
	<title>Home</title>
	
</head>
<body>

<p>MAIN PAGE BODY</p>
<c:if test="${roleSession eq 'P'}">
<a target="_blank" style="color:white;box-shadow:none;" href="${url_newProblemForm}">
<span style="color: #094FA4; height: 16px; width: 16px;font-size: 15px;padding-top: 13px;">CREATE PROBLEM</span>
</a>
</c:if>

<c:if test="${roleSession eq 'P'}">
<a target="_blank" style="color:white;box-shadow:none;" href="${url_getProblemasProfesor}">
<span style="color: #094FA4; height: 16px; width: 16px;font-size: 15px;padding-top: 13px;">VER PROBLEMAS CREADOS</span>
</a>
</c:if>

<c:if test="${roleSession eq 'A'}">
<a target="_blank" style="color:white;box-shadow:none;" href="${url_getTareasAlumno}">
<span style="color: #094FA4; height: 16px; width: 16px;font-size: 15px;padding-top: 13px;">LISTA PROBLEMAS A RESOLVER</span>
</a>
</c:if>

</body>
</html>