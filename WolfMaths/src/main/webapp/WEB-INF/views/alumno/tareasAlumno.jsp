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
<spring:url value="/problem/resolverProblema" var="url_resolveProblemForm"/>
<spring:url value="/alumno/obtenerTareasAlumno" var="url_getTareasAlumno"/>
	<title>Home</title>
	
</head>
<body>

<p>ALUMNO TAREAS BODY</p>

<form:form id="formTareasAlumno" name="formTareasAlumno" modelAttribute="tareasAlumnoForm">
<c:forEach items="${tareasAlumnoForm.listaTareasAlumno}" var="tareaAlumno">
<p>ID : ${tareaAlumno.id}</p>
<p>Nombre Asignatura : ${tareaAlumno.asignatura.nombre}</p>
<p>Profesor Creador Problema : ${tareaAlumno.profesor.nombre}</p>
<p>Resumen Tarea : ${tareaAlumno.resumen}</p>
<a target="_blank" style="color:white;box-shadow:none;" href="${url_resolveProblemForm}/${tareaAlumno.id}">
<span style="color: #094FA4; height: 16px; width: 16px;font-size: 15px;padding-top: 13px;">HOLA</span>
</a>
</div>
</c:forEach>


</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>