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
<spring:url value="/alumno/obtenerTareasAlumno" var="url_getTareasAlumno"/>
<spring:url value="/profesor/obtenerResolucionesAlumnosProblema" var="url_getResolucionesAlumnosProblema"/>
	<title>Home</title>
	
</head>
<body>

<p>PROBLEMAS PROFESOR BODY</p>

<form:form id="problemasProfesorForm" name="problemasProfesorForm" modelAttribute="problemasProfesorForm">
<c:forEach items="${problemasProfesorForm.listaProblemaProfesor}" var="problemaProfesor">
<p>ID : ${problemaProfesor.id}</p>
<p>Nombre Asignatura : ${problemaProfesor.asignatura.nombre}</p>
<p>Numero de Alumnos que han realizado el problema : ${problemaProfesor.numeroResolucionesAlumnos}</p>
<p>Resumen Tarea : ${problemaProfesor.resumen}</p>
<a target="_blank" style="color:white;box-shadow:none;" href="${url_getResolucionesAlumnosProblema}/${problemaProfesor.id}">
<span style="color: #094FA4; height: 16px; width: 16px;font-size: 15px;padding-top: 13px;">HOLA</span>
</a>
</div>
</c:forEach>


</form:form>

</body>
</html>