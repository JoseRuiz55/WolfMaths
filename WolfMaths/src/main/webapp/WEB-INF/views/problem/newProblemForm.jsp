<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Creación de Problemas</title>
<script type="text/javascript"
	src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="https://src.springframework.org/svn/spring-samples/mvc-ajax/trunk/src/main/webapp/resources/json.min.js" /> "></script>
</head>
<body>
	<div id="initialDivTables">
		<table id="tableProblem" style="height:200px;width:45%;float:left;">
			<tr>
				<td>Enunciado Problema</td>
			</tr>
			<tr>
				<td><textarea rows="4" cols="50"></textarea></td>
			</tr>
			
		</table>
		<div id="divTableActions" style="overflow:scroll;height:200px;width:45%;float:rigth;">
		<table id="tableActions" >
			<tr>
				<td>Acciones realizadas</td>
			</tr>
		</table>
		</div>
	</div>
	<table id="tableSelectNumVariables">
	<tr>
				<td>Número de Variables</td>
			</tr>
			<tr>
				<td>
						<select id="selectVariables" >
							<option	value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
						<input type="button" onclick="createVariablesTable()" value="Generar Variables"/>
				</td>
			</tr>
			<tr>
			
			</tr>
	</table>

	<div id="divVariables" style="padding-left: 0">
	
	<table id="tableVariables"></table>
	<br/>
	<div id="divEnunciadoVariables"></div>
	<br/>
	</div>
	
	
	<div id="divResolutionSteps" >
	<table id="tableResolutionSteps">
	
	</table>
	<br/></div>

</body>
<script>
	function createVariablesTable() {
		var numVars = $('#selectVariables').val();
		//alert("Numero de Variables Seleccionadas: " + numVars);
		createVarsTrs(numVars);
		addTrAction("Seleccion de "+numVars+" variables para la resolución del problema");
		/* alert(username);
		$.postJSON("eliminarUsuario", username.replace("\"", ""),
				function(data) {
					alert(data.accion);
					$('#accionRealizada').text(data.accion);
					alert(username);
					document.getElementById('tr' + username).remove();
				} );*/
	}
	
	function createVarsTrs(numVars)
	{
		var html = '';
		//Eliminamos la implementacion anterior en caso de existir
		$('#tableVariables').empty();
		for (var i = 0;i<numVars;i++)
			{
				console.log("Valor i: ");
				console.log(i);
				var nameVar = getNameVar(i);
				console.log("Letra asignada al valor: ");
				console.log(nameVar);
				html=html+'<tr>';
				html=html+'<td>'+nameVar+'=</td><td><input id="inputVar'+nameVar.toUpperCase()+'"></input></td>';
				html=html+'</tr>';
			}
		//VariableResultado
		html=html+'<tr>';
		html=html+'<td>Resultado=</td><td><input id="inputVarResultado"></input></td>';
		html=html+'</tr>';
		//FinVariableResultado
		console.log(html);
		$('#tableVariables').append(html);
		html='<label>Numero de Paso de Resolucion a Implementar</label>';
		html ='<label>'+getSelectButtonResolutionSteps()+'</label>';
		console.log(html);
		$('#divEnunciadoVariables').empty();
		$('#divEnunciadoVariables').append(html);
		
		//$(html).appendTo('#trVariables');
	}
	
	function getNameVar(numVar)
	{
		switch(parseInt(numVar)) {
	    case 0:
	        return "x";
	        break;
	    case 1:
	        return "y";
	        break;
	    case 2:
	        return "z";
	        break;
	    case 3:
	        return "k";
	        break;
	    default:
	        return "ERROR";
		} 
	}
	
	function getSelectButtonResolutionSteps()
	{
		var selectButtonHtml = '';
		selectButtonHtml = selectButtonHtml+
		'<select id="selectResolutionSteps" >'+
		'<option	value="1">1</option>'+
		'<option value="2">2</option>'+
		'<option value="3">3</option>'+
		'<option value="4">4</option>'+
		'</select><td>'+
		'<input type="button" onclick="createResolutionStepsTable()" value="Generar Pasos de Resolucion"/></td>';
		console.log(selectButtonHtml);
		return selectButtonHtml;
	}
	
	function createResolutionStepsTable()
	{
		
		var numSteps = $('#selectResolutionSteps').val();
		//alert("Numero de Variables Seleccionadas: " + numVars);
		createStepsTrs(numSteps);
		addTrAction("Seleccion de "+numSteps+" pasos para la resolución del problema");
		
	}
	
	function createStepsTrs(numSteps)
	{
		var html = '';
		//Eliminamos la implementacion anterior en caso de existir
		$('#tableResolutionSteps').empty();
		for (var i = 0;i<numSteps;i++)
			{
				console.log("Valor i: ");
				console.log(i);
				var numStep = getStepsNum(i);
				console.log("Paso asignada al valor: ");
				console.log(numStep);
				html=html+'<tr>';
				html=html+'<td>Paso Número: '+numStep+'</td><td><input name="inputStep'+numStep.toUpperCase()+'" id="inputStep'+numStep.toUpperCase()+'"></input></td><td><input type="button" value="Ejecutar Paso" onclick="ejecutarPaso('+numStep+')"></input></td>';
				html=html+'</tr>';
			}
		
		html=html+'<tr>';
		html=html+'<td>Resultado Final: </td><td><input name="inputStepResultado" id="inputStepResultado"></input></td><td><input type="button" value="Ejecutar Paso" onclick="ejecutarResolucion()"></input></td>';
		html=html+'</tr>';
		
		console.log(html);
		$('#tableResolutionSteps').append(html);
		/*html='<label>Numero de Paso de Resolucion a Implementar</label>';
		html ='<label>'+getSelectButtonResolutionSteps()+'</label>';
		console.log(html);
		$('#divEnunciadoVariables').empty();
		$('#divEnunciadoVariables').append(html);*/
		//$(html).appendTo('#trVariables');
	}
	
	
	function getStepsNum(numStep)
	{
		switch(parseInt(numStep)) {
	    case 0:
	        return "1";
	        break;
	    case 1:
	        return "2";
	        break;
	    case 2:
	        return "3";
	        break;
	    case 3:
	        return "4";
	        break;
	    default:
	        return "ERROR";
		} 
	}
	
	function addTrAction(accion)
	{
		$('#tableActions').append('<tr><td>'+accion+'</tr></td>');
	}
	
	/*function ejecutarPaso(numPaso)
	{
		$.postJSON("ejecutarPaso", numPaso,
				function(data) {
					alert(data.accion);
					addTrAction(data.accion);
				});
	}*/
	
	function ejecutarPaso(numPaso)
	{
		$.ajax({  
		     type : "POST",   
		     url : "ejecutarPaso",   
		     data : "step=" + numPaso + "&stepExecution=" +$('#inputStep'+numPaso).val(),
		     success : function(response) {  
		      addTrAction(response);   
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
	}
	
</script>
</html>