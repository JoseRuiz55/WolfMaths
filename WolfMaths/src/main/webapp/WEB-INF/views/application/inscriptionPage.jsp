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
<spring:url value="/sessionController/inscribirUsuario" var="url_newInscripcionForm" />


</head>
<body>
<form:form id="inscriptionForm" name="inscriptionForm" modelAttribute="registrationForm" action="${url_newInscripcionForm}" method="POST">
<div id="menu">
<div class="panel list-group">
 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sm" data-parent="#menu">ASIGNATURAS <span class="label label-info">${fn:length(registrationForm.listaAsignaturas)}</span> <span class="glyphicon glyphicon-tag pull-right"></span></a>
 <div id="sm" class="sublinks collapse">
 <c:forEach var="asignatura" items="${registrationForm.listaAsignaturas}">
  <a class="list-group-item small"><span class="glyphicon glyphicon-chevron-right"></span>${asignatura.nombre}</a>
  </c:forEach>
 </div>
 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" data-parent="#menu">PROFESORES <span class="label label-info">${fn:length(registrationForm.listaProfesores)}</span> <span class="glyphicon glyphicon-tag pull-right"></span></a>
 <div id="sl" class="sublinks collapse">
  <c:forEach var="profesor" items="${registrationForm.listaProfesores}">
  <a class="list-group-item small"><span class="glyphicon glyphicon-chevron-right"></span>${profesor.nombre}</a>
  </c:forEach>
 </div>
 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" data-parent="#menu">ALUMNOS <span class="label label-info">${fn:length(registrationForm.listaAlumnos)}</span> <span class="glyphicon glyphicon-tag pull-right"></span></a>
 <div id="sl" class="sublinks collapse">
  <c:forEach var="profesor" items="${registrationForm.listaAlumnos}">
  <a class="list-group-item small"><span class="glyphicon glyphicon-chevron-right"></span>${alumno.nombre}</a>
  </c:forEach>
 </div>
 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" data-parent="#menu">ADMINISTRADORES <span class="label label-info">${fn:length(registrationForm.listaAdministradores)}</span> <span class="glyphicon glyphicon-tag pull-right"></span></a>
 <div id="sl" class="sublinks collapse">
  <c:forEach var="profesor" items="${registrationForm.listaAdministradores}">
  <a class="list-group-item small"><span class="glyphicon glyphicon-chevron-right"></span>${administrador.nombre}</a>
  </c:forEach>
 </div>
</div>
</div>
</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
<script>
function assignFormToRole(){
	var rol = $('#selectRolId').val();
	$('#'+rol+'Nombre').val($('#nombre').val());
	$('#'+rol+'Apellido1').val($('#apellido1').val());
	$('#'+rol+'Apellido2').val($('#apellido2').val());
	$('#'+rol+'Username').val($('#username').val());
	$('#'+rol+'Password').val($('#password').val());
	$('#'+rol+'Telefono').val($('#telefono').val());
	$('#'+rol+'Email').val($('#email').val());
	document.getElementById('registrationForm').submit();
}
</script>
</html>