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

<p>SOLUCIONES ALUMNOS PROBLEMA BODY</p>


	<div class="contTable">
		<table id="tabla_listado_expedientes" cellspacing="0"
			class="generic sortable">
			<thead class="scrollHeader">
				<tr>
					<th class="txt-centrado" style="width: 100px;">Alumno</th>
					<th class="txt-centrado" style="width: 100px;">Numero Pasos Resolucion</th>
					<th class="txt-centrado" style="width: 100px;">Resultado</th>
					<th class="txt-centrado" style="width: 100px;">Nota</th>
					<th class="txt-centrado" style="width: 100px;">Nota Comentario</th>
				</tr>
			</thead>
			<tbody class="scrollContent">
				<c:forEach var="problema"
					items="${problemasProfesorForm.listaSolucionProblemaAlumno}"
					varStatus="rowCounter">
					<c:set var="myInteger" value="${myInteger+1}"></c:set>
					<c:set var="rowStyle" scope="page" value="" />
					<c:if test="${rowCounter.count % 2 == 0}">
						<c:set var="rowStyle" scope="page" value="fondo_celda" />
					</c:if>
					<tr class="${rowStyle}" id="exp_${expediente.codigoExpediente}">
						
						<td class="txt-centrado">
							<p style="white-space: pre-wrap;">${problema.alumno.nombre} ${problema.alumno.apellido1}</p>
						</td>
						<td class="txt-centrado">
							<p style="white-space: pre-wrap;">${problema.problemaCorreccionAlumno.pasosResolucion}</p>
						</td>
						<td class="txt-centrado">
							<p style="white-space: pre-wrap;">${problema.problemaCorreccionAlumno.resultado} - ${problema.problemaCorreccionAlumno.id}</p>
						</td>
						<th class="has-pretty-child"><label class="title1">${problema.correccion.nota}</label></th>
						<td class="txt-centrado">
							<p style="white-space: pre-wrap;">${problema.correccion.comentario}</p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>