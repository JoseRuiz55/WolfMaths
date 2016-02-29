<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Creación de Problemas</title>
<spring:url var="problem_creation_url" value="/resourcesApl/js/wolfmathsProblemCreation.js" />
<spring:url var="global_functions_url" value="/resourcesApl/js/wolfmathsGlobalFunctions.js" />
<spring:url value="/expedientes/detalleRiesgo/" var="url_detalleRiesgo"/>
<script type="text/javascript"
	src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="https://src.springframework.org/svn/spring-samples/mvc-ajax/trunk/src/main/webapp/resources/json.min.js" /> "></script>
<script src="${problem_creation_url}"></script>
<script src="${global_functions_url}"></script>

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
						<input id="inputNumVariables" name="inputNumVariables" type="hidden" value=""/>
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
	
	<div id="divConfirmarCreacionProblema" >
	</div>

</body>
<script>

	var x;
	var y;
	var z;
	var k;
	var numVars;
	var resultado;
	
	function readStepExecution(numPaso){
		var inputStep = $('#inputStep'+numPaso).val();
		inputStep = replaceSpaces(inputStep);
		console.log("Sentencia Leida de la ejecución del inputStep"+inputStep);
		inputStep = getVariablesValueToInputStep(inputStep);
		console.log("Sentencia despues de la transformación: "+inputStep);
		var varToAssign = "";
		varToAssign = getVarToAssign(inputStep);
		console.log("Variable a asignar el valor: "+varToAssign);
		if(varToAssign!=""){
			inputStep = inputStep.split(varToAssign)[1];
			console.log("inputStep a ejecutar: "+inputStep);
			executeSentence(varToAssign,inputStep);
		}else{
			stepExecution(numPaso);
		}
		$('#stepButton'+numPaso).attr("disabled","disabled");
		if(numPaso==-1){
			alert("Deja de jugar con el JS");
		}

	}
	
	//Ejecución una operación que devolverá un valor de una variable
	function stepExecution(numPaso)
	{
		console.log("Valor"+$('#inputStep'+numPaso).val());
		var inputStep = $('#inputStep'+numPaso).val();
		inputStep = replaceSpaces(inputStep);
		$.ajax({  
		     type : "POST",   
		     url : "ejecutarSentencia",   
		     data : "&sentenceToExecute=" +inputStep,
		     success : function(response) {  
		      addTrAction(response);
		      if(response.indexOf(API_RESULT_SEPARATOR) > -1){
		    	  var varToAssign = "";
				  varToAssign = getVarToAssign(response);
				  console.log("Variable a asignar el valor: "+varToAssign);
				  if(varToAssign!=""){
					value = response.split(API_RESULT_SEPARATOR)[1].split(varToAssign)[1];
					value = value.replace("?","");
					value = parseFloat(value).toFixed(2).toString();
					assignValueToASingleVar(varToAssign,value);
					setVariablesValues();
				  }else{
					alert("Fallo al ejecutar la lógica");
				  } 
		      }
		       
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
	}
	
	//Ejecución que devolvera un resultado y este será asignado a la variable que se le pasa en varToAssign
	function executeSentence(varToAssign,sentenceToExecute)
	{
		console.log("Valor"+sentenceToExecute);
		sentence = replaceSpaces(sentenceToExecute);
		$.ajax({  
		     type : "POST",   
		     url : "ejecutarSentencia",   
		     data : "&sentenceToExecute=" +sentence,
		     success : function(response) {  
		      if(response.indexOf(API_RESULT_SEPARATOR) > -1){
		    	  addTrAction(response);
		    	  result = response.split(API_RESULT_SEPARATOR)[1];
		    	  result = result.replace("?","");
		    	  result = parseFloat(result).toFixed(2).toString();
		    	  console.log("result de executeSentence: "+result);
				  assignValueToASingleVar(varToAssign,result);
				  setVariablesValues();
		      }
		        
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });  
	}
	
	function ejecutarResolucion(){
		var inputStep = $('#inputStepResultado').val();
		inputStep = replaceSpaces(inputStep);
		console.log("Sentencia Leida de la ejecución del inputStep"+inputStep);
		inputStep = getVariablesValueToInputStep(inputStep);
		console.log("Sentencia despues de la transformación: "+inputStep);
		$.ajax({  
		     type : "POST",   
		     url : "ejecutarSentencia",   
		     data : "&sentenceToExecute=" +inputStep,
		     success : function(response) {  
		      addTrAction(response);
		      if(response.indexOf(API_RESULT_SEPARATOR) > -1){
		    	  value = response.split(API_RESULT_SEPARATOR)[1];
		    	  if(value.indexOf('=')>-1){
		    		  alert("Fallo al ejecutar la lógica");
		    	  }else{
		    		  assignValueToResult(value);
		    		  addButtonSaveProblem();
		    		  $('#inputStepResultado').attr("disabled","disabled");
		    	  }
		    	  
		    	  /*var varToAssign = "";
				  varToAssign = getVarToAssign(response);
				  console.log("Variable a asignar el valor: "+varToAssign);
				  if(varToAssign!=""){
					value = response.split(API_RESULT_SEPARATOR)[1].split(varToAssign)[1];
					assignValueToASingleVar(varToAssign,value);
					setVariablesValues();
				  }else{
					
				  }*/
		      }
		       
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });
	}
	
	function assignValueToResult(res){
		$('#inputVarResultado').val(res);
		resultado=$('#inputVarResultado').val();
	}
	function addButtonSaveProblem(){
		$('#divConfirmarCreacionProblema').append('<input type="button" id="saveProblem" onclick="saveProblem()" value="Guardar Problema"></input>');
	}
	
	function saveProblem(){
		if(confirm("Desea guardar el problema?")){
		$.ajax({  
		     type : "POST",   
		     url : "guardarProblema",
		     success : function(response) {
		    	 
		   	 alert("Problema guardado");
		     /*addTrAction(response);
		      if(response.indexOf(API_RESULT_SEPARATOR) > -1){
		    	  value = response.split(API_RESULT_SEPARATOR)[1];
		    	  if(value.indexOf('=')>-1){
		    		  alert("Fallo al ejecutar la lógica");
		    	  }else{
		    		  assignValueToResult(value);
		    		  addButtonSaveProblem();
		    		  $('#inputStepResultado').attr("disabled","disabled");
		    	  }
		    	  
		    	  /*var varToAssign = "";
				  varToAssign = getVarToAssign(response);
				  console.log("Variable a asignar el valor: "+varToAssign);
				  if(varToAssign!=""){
					value = response.split(API_RESULT_SEPARATOR)[1].split(varToAssign)[1];
					assignValueToASingleVar(varToAssign,value);
					setVariablesValues();
				  }else{
					
				  }*/
		      }
		       
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });
		}else{
			
		}
	}
	
	
	function getValorX(){
		console.log(x);
		return x;
	}
	
	
	
	
	
</script>
</html>