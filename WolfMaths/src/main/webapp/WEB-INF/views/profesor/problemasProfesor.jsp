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
<div>
<p>ID : ${problemaProfesor.id}</p>
<p>Nombre Asignatura : ${problemaProfesor.asignatura.nombre}</p>
<p>Numero de Alumnos que han realizado el problema : ${problemaProfesor.numeroResolucionesAlumnos}</p>
<p>Resumen Tarea : ${problemaProfesor.resumen}</p>
<a target="_blank" style="color:white;box-shadow:none;" href="${url_getResolucionesAlumnosProblema}/${problemaProfesor.id}">
<span style="color: #094FA4; height: 16px; width: 16px;font-size: 15px;padding-top: 13px;">HOLA</span>
</a>
</div>
</c:forEach>

<div class="row">
            <div class=" col-xs-12 col-sm-12 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2">
                <div class="db-wrapper">
                    <div class="db-pricing-nine">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th style="color:black; vertical-align: middle;">Nombre Asignatura</th>
                                        <th style="color:black; vertical-align: middle;">Departamento</th>
                                        <th style="color:black; vertical-align: middle;" class="">Soluciones</th>
                                        <th style="color:black; vertical-align: middle;" class="">Resumen Tarea</th>
                                        <th style="color:black; vertical-align: middle;" class="">Acción</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${problemasProfesorForm.listaProblemaProfesor}" var="problemaProfesor">
									 <tr>
                                        <td style="vertical-align: middle;" class="db-width-perticular">${problemaProfesor.asignatura.nombre}</td>
                                        <td style="vertical-align: middle;"  class="db-width-perticular">${problemaProfesor.asignatura.departamento}</td>
                                        <td style="vertical-align: middle;" >${problemaProfesor.numeroResolucionesAlumnos}</td>
                                        <td style="vertical-align: middle;" >${problemaProfesor.resumen}</td>
                                        <td style="vertical-align: middle;" ><a href="${url_getResolucionesAlumnosProblema}/${problemaProfesor.id}" class="btn db-button-color-three">Corregir pendientes</a></td>
                                    </tr>
									</c:forEach>
                                    <tr>
                                        <td class="db-width-perticular">20 GB Hard Disk Included</td>
                                        <td>N/A</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">120 TB Bandwidth</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td>N/A</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">Dedicated Support Panel</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">Extra Plugin Features</td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">E-mail Support</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">Chat Support Option</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">Dedicated Support Panel</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">Extra Plugin Features</td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">E-mail Support</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular">Chat Support Option</td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                        <td><i class="glyphicon glyphicon-remove icon-red"></i></td>
                                        <td><i class="glyphicon glyphicon-ok icon-green"></i></td>
                                    </tr>
                                    <tr>
                                        <td class="db-width-perticular"></td>
                                        <td><a href="#" class="btn db-button-color-one">BUY PLAN</a> </td>
                                        <td><a href="#" class="btn db-button-color-two">BUY PLAN</a> </td>
                                        <td><a href="#" class="btn db-button-color-three">BUY PLAN</a> </td>
                                        <td><a href="#" class="btn db-button-color-three">BUY PLAN</a> </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>