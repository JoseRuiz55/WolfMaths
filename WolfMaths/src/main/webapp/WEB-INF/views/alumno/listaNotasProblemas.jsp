<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<jsp:include page="/WEB-INF/views/header.jsp" />
<head>
<spring:url value="/problem/createProblemForm" var="url_newProblemForm" />
<spring:url value="/alumno/obtenerTareasAlumno"
	var="url_getTareasAlumno" />
<spring:url value="/profesor/obtenerResolucionesAlumnosProblema"
	var="url_getResolucionesAlumnosProblema" />
<spring:url value="/problem/evaluarResolucionesAlumnosProblema/"
	var="url_evaluarSolucionAlumno" />
	<spring:url value="/profesor/autoCorregir/"
	var="url_autoCorregirSolucionAlumno" />
<spring:url value="/alumno/consultarHistoricoNotas/"
	var="url_historicoNotasProblemaAlumno" />

</head>
<body>



<div class="contTable">
	<table class="table well">
		<thead>
			<tr>
				<th>Asignatura</th>
				<th>Departamento</th>
				<th>Pasos Resolucion</th>
				<th>Profesor</th>
				<th>Resultado</th>
				<th>Nota</th>
				<th>Comentario</th>
			</tr>
		</thead>
		<tbody>
		
				<c:forEach var="problema"
					items="${tareasAlumnoForm.listaCorreccionProblemaAlumno}"
					varStatus="rowCounter">
					<c:set var="trStyle" value="active"/>
					<c:if test="${not empty problema.correccion.nota}">
						<fmt:parseNumber var="notaValue" type="number" value="${fn:replace(problema.correccion.nota,'.',',')}" />
						<c:if test="${notaValue le 4.99}">
						<c:set var="trStyle" value="danger"/>
						</c:if>
						<c:if test="${notaValue ge 5}">
						<c:set var="trStyle" value="success"/>
						</c:if>
					</c:if>
					
					<tr class="${trStyle}" id="correccion_Alumno_${problema.correccion.id}">

						<td>
							<p style="white-space: pre-wrap;">${problema.asignatura.nombre}</p>
						</td>
						<td>
							<p style="white-space: pre-wrap;">${problema.asignatura.departamento}</p>
						</td>
						<td>
							<p style="white-space: pre-wrap;">${problema.problemaCorreccionAlumno.pasosResolucion}</p>
						</td>
						<td>
							<p style="white-space: pre-wrap;">${problema.profesor.nombre} ${problema.profesor.apellido1}</p>
						</td>
						<td>
							<p style="white-space: pre-wrap;">${problema.problemaCorreccionAlumno.resultado}</p>
						</td>
						<td><p style="white-space: pre-wrap;">${problema.correccion.nota}</p></td>
						<td><p style="white-space: pre-wrap;">${problema.correccion.comentario}<p></td>
						<td><a href="${url_historicoNotasProblemaAlumno}${problema.problemaCorreccionAlumno.id}" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-time"></span> Histórico Notas</a></td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>