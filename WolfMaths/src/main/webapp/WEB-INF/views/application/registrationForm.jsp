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
<spring:url value="/sessionController/registerUser" var="url_newUserForm" />


</head>
<body>
<form:form id="registrationForm" name="registrationForm" modelAttribute="registrationForm" action="${url_newUserForm}" method="POST">
<div class="container">
    <h1 class="well">Registration Form</h1>
	<div class="col-lg-12 well">
	<div class="row">
				<form>
				
					<div class="col-sm-12">
						<div class="row">
								<div class="col-sm-6 form-group">
									<label>Rol</label>
										<form:select id="selectRolId" path="rol" class="form-control">
										  <option value="ADMINISTRADOR">Administrador</option>
										  <option value="ALUMNO">Alumno</option>
										  <option value="PROFESOR">Profesor</option>
										</form:select>
								</div>
						</div>	
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Nombre</label>
								<input id="nombre" type="text" placeholder="Nombre" class="form-control">
								<form:input id="ADMINISTRADORNombre" type="hidden" path="administrador.nombre" />
								<form:input id="PROFESORNombre" type="hidden" path="profesor.nombre" />
								<form:input id="ALUMNONombre" type="hidden" path="alumno.nombre" />
							</div>
						</div>
									
						<div class="form-group">
							<label>Primer Apellido</label>
							<input id="apellido1" type="text" placeholder="Primer Apellido" class="form-control">
							<form:input id="ADMINISTRADORApellido1" type="hidden" path="administrador.apellido1" />
							<form:input id="PROFESORApellido1" type="hidden" path="profesor.apellido1" />
							<form:input id="ALUMNOApellido1" type="hidden" path="alumno.apellido1" />
						</div>
						<div class="form-group">
							<label>Segundo Apellido</label>
							<input id="apellido2" type="text" placeholder="Segundo Apellido" class="form-control">
							<form:input id="ADMINISTRADORApellido2" type="hidden" path="administrador.apellido2" />
							<form:input id="PROFESORApellido2" type="hidden" path="profesor.apellido2" />
							<form:input id="ALUMNOApellido2" type="hidden" path="alumno.apellido2" />
						</div>	
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Username</label>
								<input id="username" type="text" placeholder="Username" class="form-control">
								<form:input id="ADMINISTRADORUsername" type="hidden" path="administrador.username" />
								<form:input id="PROFESORUsername" type="hidden" path="profesor.username" />
								<form:input id="ALUMNOUsername" type="hidden" path="alumno.username" />
							</div>	
							<div class="col-sm-4 form-group">
								<label>Password</label>
								<input id="password" type="password" placeholder="Password" class="form-control">
								<form:input id="ADMINISTRADORPassword" type="hidden" path="administrador.password" />
								<form:input id="PROFESORPassword" type="hidden" path="profesor.password" />
								<form:input id="ALUMNOPassword" type="hidden" path="alumno.password" />
							</div>	
						</div>					
					<div class="form-group">
						<label>Número de Teléfono</label>
						<input id="telefono" type="text" placeholder="Número de Teléfono" class="form-control">
						<form:input id="ADMINISTRADORTelefono" type="hidden" path="administrador.telefono" />
						<form:input id="PROFESORTelefono" type="hidden" path="profesor.telefono" />
						<form:input id="ALUMNOTelefono" type="hidden" path="alumno.telefono" />
					</div>		
					<div class="form-group">
						<label>Correo Electrónico</label>
						<input id="email" type="text" placeholder="Correo Electrónico" class="form-control">
						<form:input id="ADMINISTRADOREmail" type="hidden" path="administrador.email" />
						<form:input id="PROFESOREmail" type="hidden" path="profesor.email" />
						<form:input id="ALUMNOEmail" type="hidden" path="alumno.email" />
					</div>	
					<button onclick="assignFormToRole();" type="button" class="btn btn-lg btn-info">Crear Usuario</button>					
					</div>
				</form> 
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