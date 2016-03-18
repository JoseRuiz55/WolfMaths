<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WolfMaths</title>

<!-- URL CSS -->
<spring:url value="/resourcesApl/styles/corev4_BBVA.css"
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
	var="bootstrap_css" />

<spring:url var="problem_creation_url"
	value="/resourcesApl/js/wolfmathsProblemCreation.js" />
<spring:url var="global_functions_url"
	value="/resourcesApl/js/wolfmathsGlobalFunctions.js" />
	
<spring:url value="/sessionController/logout" var="url_logout"/>

<!-- IMPORT CSS -->
<link rel="stylesheet" type="text/css" href="${jquery_ui_css}"></link>
<link rel="stylesheet" type="text/css" href="${jquery_ui_structure_css}"></link>
<link rel="stylesheet" type="text/css" href="${jquery_ui_theme_css}"></link>
<link rel="stylesheet" type="text/css" href="${css_estilos_banco}"></link>

<link rel="stylesheet" type="text/css" href="${estilos_enro}"></link>
<link rel="stylesheet" type="text/css" href="${estilos_expedientes}"></link>
<link rel="stylesheet" type="text/css" href="${bootstrap_css}"></link>

<script type="text/javascript"
	src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="https://src.springframework.org/svn/spring-samples/mvc-ajax/trunk/src/main/webapp/resources/json.min.js" /> "></script>
<script src="${problem_creation_url}"></script>
<script src="${global_functions_url}"></script>


</head>
<body>

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


</body>
</html>