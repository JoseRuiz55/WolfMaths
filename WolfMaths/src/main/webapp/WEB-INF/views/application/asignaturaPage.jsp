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
<spring:url value="/asignatura/crearAsignatura"
	var="url_nuevaAsignaturaUrl" />
<spring:url value="/asignatura/editarAsignatura"
	var="url_editarAsignaturaUrl" />
<spring:url value="/asignatura/guardarAsignatura"
	var="url_guardarAsignaturaUrl" />
<spring:url value="/inscripcion" var="url_volverAdminPanelUrl" />
<c:set var="readOnlyVars" value="false" />

<c:if test="${registrationForm.creacion }">
	<c:set value="${url_nuevaAsignaturaUrl}"
		var="url_asignaturaFormActionUrl" />
</c:if>

<c:if test="${registrationForm.visualizar }">
	<c:set value="${url_editarAsignaturaUrl}"
		var="url_asignaturaFormActionUrl" />
	<c:set var="readOnlyVars" value="true" />
</c:if>

<c:if test="${registrationForm.editar }">
	<c:set value="${url_guardarAsignaturaUrl}"
		var="url_asignaturaFormActionUrl" />
</c:if>



</head>
<body>
	<form:form id="asignatureForm" name="asignatureForm"
		modelAttribute="registrationForm"
		action="${url_asignaturaFormActionUrl}" method="POST">
		<div class="container">
			<h1 class="well">Asignatura Form</h1>
			<div class="col-lg-12 well">
				<div class="row">
					<form>

						<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Nombre</label>
									<form:input type="hidden" id="idAsignaturaId"
										path="asignatura.id" />
									<form:input id="idAsignaturaNombre" class="form-control"
										path="asignatura.nombre" readonly="${readOnlyVars }" />
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Departamento</label>
									<form:input id="idAsignaturaDepartamento" class="form-control"
										path="asignatura.departamento" readonly="${readOnlyVars }" />
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Número Máximo de Alumnos</label>
									<form:input id="idAsignaturaMaxAlum" class="form-control"
										path="asignatura.maxAlum" readonly="${readOnlyVars }" />
								</div>
							</div>
							<c:if test="${registrationForm.creacion}">
								<button onclick="realizarAccionAsignaturaForm();" type="button"
									class="btn btn-lg btn-info">Crear Asignatura</button>
								<a href="${url_volverAdminPanelUrl}" type="button"
									class="btn btn-lg btn-info">Volver</a>
							</c:if>
							<c:if test="${registrationForm.visualizar}">
								<button id="editarAsigButton" onclick="realizarAccionAsignaturaForm();" type="button"
									class="btn btn-lg btn-info">Editar Asignatura</button>
								<button id="borrarAsigButton"
									onclick="borrarAsignaturaAction(${registrationForm.asignatura.id});"
									type="button" class="btn btn-lg btn-info">Borrar
									Asignatura</button>
								<a href="${url_volverAdminPanelUrl}" type="button"
									class="btn btn-lg btn-info">Volver</a>
							</c:if>
							<c:if test="${registrationForm.editar}">
								<button onclick="realizarAccionAsignaturaForm();" type="button"
									class="btn btn-lg btn-info">Guardar Cambios</button>
								<a href="${url_volverAdminPanelUrl}" type="button"
									class="btn btn-lg btn-info">Volver</a>
							</c:if>

						</div>
					</form>
				</div>
			</div>
		</div>
	</form:form>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
<script>
function realizarAccionAsignaturaForm(){
	document.getElementById('asignatureForm').submit();
}
function borrarAsignaturaAction(idAsignatura){
		var id = $('#idAsignaturaId').val();
		$.ajax({  
		     type : "POST",   
		     url : "/wolfmaths/asignatura/eliminarAsignatura/"+idAsignatura+"",
		     success : function(response) {
		    	 $('#editarAsigButton').hide();
		    	 $('#borrarAsigButton').hide();
		      	alert("Borrado realizado");
		      },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });

}
function volverAdministrationPannel(){
	$.ajax({  
	     type : "GET",   
	     url : "/wolfmaths/inscripcion",
	     success : function(response) {  
	      },  
	     error : function(e) {  
	      alert('Error: ' + e);   
	     }  
	    });
}
</script>
</html>