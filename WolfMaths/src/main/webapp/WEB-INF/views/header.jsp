<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>WolfMaths</title>

<!-- URL CSS -->
<!--<spring:url value="/resourcesApl/styles/corev4_BBVA.css"
	var="css_estilos_banco" />
<spring:url value="/resourcesApl/styles/estilosENRO.css"
	var="estilos_enro" />
<spring:url value="/resourcesApl/styles/expedientesro.css"
	var="estilos_expedientes" />
<spring:url value="/resourcesApl/styles/jquery-ui-1.11.3/jquery-ui.css"
	var="jquery_ui_css" />
<spring:url
	value="/resourcesApl/styles/jquery-ui-1.11.3/jquery-ui.structure.css"
	var="jquery_ui_structure_css" />
<spring:url
	value="/resourcesApl/styles/jquery-ui-1.11.3/jquery-ui.theme.css"
	var="jquery_ui_theme_css" />
<spring:url value="/resourcesApl/styles/bootstrap.css"
	var="bootstrap_css" />-->
<!-- URL CSS -->
<spring:url
	value="/resourcesApl/styles/startbootstrap-heroic-features-1.0.4/css/bootstrap.css"
	var="startbootstrap_bootstrap_css" />
	<spring:url
	value="/resourcesApl/styles/startbootstrap-heroic-features-1.0.4/css/heroic-features.css"
	var="startbootstrap_heroic_css" />
	<spring:url
	value="/resourcesApl/styles/startbootstrap-heroic-features-1.0.4/css/bootstrap.min.css"
	var="startbootstrap_bootstrapmin_css" />
	<spring:url
	value="/resourcesApl/styles/header_styles.css"
	var="headerMenu_css" />
	<spring:url
	value="/resourcesApl/styles/wolfmaths.css"
	var="wolfmaths_css" />
	<spring:url
	value="/resourcesApl/styles/table_colour.css"
	var="tableColour_css" />
	

<spring:url var="problem_creation_url"
	value="/resourcesApl/js/wolfmathsProblemCreation.js" />
<spring:url var="global_functions_url"
	value="/resourcesApl/js/wolfmathsGlobalFunctions.js" />
	
	
<spring:url var="js_bootstrap_url" value="/resourcesApl/styles/startbootstrap-heroic-features-1.0.4/js/bootstrap.js" />
<spring:url var="js_bootstrapmin_url" value="/resourcesApl/styles/startbootstrap-heroic-features-1.0.4/js/bootstrap.js" />

	
<spring:url value="/sessionController/logout" var="url_logout"/>

<spring:url value="/problem/createProblemForm" var="url_newProblemForm"/>
<spring:url value="/problem/resolveProblemForm" var="url_resolveProblemForm"/>
<spring:url value="/alumno/obtenerTareasAlumno" var="url_getTareasAlumno"/>
<spring:url value="/alumno/consultarCalificaciones" var="url_getCalificacionesAlumno"/>

<spring:url value="/profesor/obtenerProblemasProfesor" var="url_getProblemasProfesor"/>

<!-- IMPORT CSS -->


<!--<link rel="stylesheet" type="text/css" href="${jquery_ui_css}"></link>
<link rel="stylesheet" type="text/css" href="${jquery_ui_structure_css}"></link>
<link rel="stylesheet" type="text/css" href="${jquery_ui_theme_css}"></link>
<link rel="stylesheet" type="text/css" href="${css_estilos_banco}"></link>

<link rel="stylesheet" type="text/css" href="${estilos_enro}"></link>
<link rel="stylesheet" type="text/css" href="${estilos_expedientes}"></link>
<link rel="stylesheet" type="text/css" href="${bootstrap_css}"></link>-->

<!-- IMPORT CSS -->
<link rel="stylesheet" type="text/css" href="${startbootstrap_bootstrap_css}"></link>
<link rel="stylesheet" type="text/css" href="${startbootstrap_heroic_css}"></link>
<link rel="stylesheet" type="text/css" href="${startbootstrap_bootstrapmin_css}"></link>
<link rel="stylesheet" type="text/css" href="${headerMenu_css}"></link>
<link rel="stylesheet" type="text/css" href="${wolfmaths_css}"></link>
<link rel="stylesheet" type="text/css" href="${tableColour_css}"></link>




<script type="text/javascript"
	src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="https://src.springframework.org/svn/spring-samples/mvc-ajax/trunk/src/main/webapp/resources/json.min.js" /> "></script>
<script src="${problem_creation_url}"></script>
<script src="${global_functions_url}"></script>

<script src="${js_bootstrap_url}"></script>
<script src="${js_bootstrapmin_url}"></script>
<c:set value="hidden" var="visibilityAlum"/>
<c:set value="hidden" var="visibilityProf"/>
<c:if test="${logedSession and roleSession eq 'A'}">
<c:set value="" var="visibilityAlum"/>
</c:if>
<c:if test="${logedSession and roleSession eq 'P'}">
<c:set value="" var="visibilityProf"/>
</c:if>





</head>
<body>
<!-- 
<p>LOGUEADO: ${logedSession}</p>
<p>Username: ${userSession.username}</p>
<p>Rol Rol Seleccionado: ${roleSession}</p>


<div class="s4-trc-container-menu">
		<div id="logOutDiv">
			<div class="translate">
				<div class="content">
					<form action="${url_logout}" method="POST">
					<a style="cursor: pointer; cursor: hand;" onclick="$(this).closest('form').submit()">
						<p style="cursor: pointer; cursor: hand;">LOGOUT</p>
					</a>
					</form>
				</div>
			</div>
		</div>	
</div>
 -->
 
 <div id='cssmenu'>
<ul>
   <li><a href='/wolfmaths/'><span>Home</span></a></li>
   <c:if test="${not logedSession}">
   <li class='active has-sub' style="visibility:hidden;"><a href='#'><span>NOLOG</span></a>
   </c:if>
   <c:if test="${logedSession and roleSession eq 'A'}">
   <li class='active has-sub' style="visibility:${visibilityAlum};"><a href='#'><span>Alumno</span></a>
      <ul>
         <li class='has-sub'><a href='${url_getTareasAlumno }'><span>Problemas a resolver</span></a>
         </li>
         <li class='has-sub'><a href='${url_getCalificacionesAlumno }'><span>Consultar Notas</span></a>
         </li>
      </ul>
   </li>
   </c:if>
   <c:if test="${logedSession and roleSession eq 'P'}">
   <li class='active has-sub' style="visibility:${visibilityProf};"><a href='#'><span>Profesor</span></a>
      <ul>
         <li class='has-sub'><a href='${url_newProblemForm}'><span>Crear Nuevo Problema</span></a>
         </li>
         <li class='has-sub'><a href='${url_getProblemasProfesor}'><span>Calificar Problemas</span></a>
         </li>
      </ul>
   </li>
   </c:if>

   <li><a href='#' style="margin-left:1350px"><span>About</span></a></li>
   <li><a href='#'><span>Contact</span></a></li>
   <li class='last' ><form action="${url_logout}" method="POST">
   <a href='#' style="" onclick="$(this).closest('form').submit()"><span>Logout</span></a>
   </form></li>
</ul>
</div>


</body>
</html>