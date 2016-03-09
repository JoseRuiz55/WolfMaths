<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Creación de Problemas</title>
<spring:url var="problem_creation_url" value="/resourcesApl/js/wolfmathsProblemCreation.js" />
<spring:url var="global_functions_url" value="/resourcesApl/js/wolfmathsGlobalFunctions.js" />

<spring:url value="/problem/createProblemForm" var="url_newProblem"/>
<spring:url value="/problem/selVarsNumber" var="url_selVarsNumber"/>
<spring:url value="/problem/selStepsNumber" var="url_selStepsNumber"/>
<spring:url value="/problem/readyToSaveProblem" var="url_readyToSaveProblem"/>
<spring:url value="/problem/guardarProblema" var="url_guardarProblema"/>
<spring:url value="/problemRest/guardarProblemaJSON" var="url_guardarProblemaJSON"/>

<c:set var="INICIO" value="INICIO"/>
<c:set var="VARSSELECCIONADAS" value="VARSSELECCIONADAS"/>
<c:set var="STEPSSELECCIONADOS" value="STEPSSELECCIONADOS"/>
<c:set var="READYTOSAVEPROBLEM" value="READYTOSAVEPROBLEM"/>


<c:set var="readOnlyVarsClass" value=""/>
<c:set var="readOnlyVars" value="false"/>

<c:set var="urlActionProblemForm" value =""/>
<c:if test="${problemForm.action eq INICIO}">
	<c:set var="urlActionProblemForm" value="${url_selVarsNumber}" />
</c:if>
<c:if test="${problemForm.action eq VARSSELECCIONADAS}">
	<c:set var="urlActionProblemForm" value="${url_selStepsNumber}" />
</c:if>
<c:if test="${problemForm.action eq STEPSSELECCIONADOS}">
	<c:set var="urlActionProblemForm" value="${url_readyToSaveProblem}" />
	<c:set var="readOnlyVars" value="true"/>
	<c:set var="readOnlyVarsClass" value="readonlyVars"/>
</c:if>

<c:if test="${problemForm.action eq READYTOSAVEPROBLEM}">
	<c:set var="urlActionProblemForm" value="${url_guardarProblema}" />
	<c:set var="readOnlyVars" value="true"/>
	<c:set var="readOnlyVarsClass" value="readonlyVars"/>
</c:if>

<script type="text/javascript"
	src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="https://src.springframework.org/svn/spring-samples/mvc-ajax/trunk/src/main/webapp/resources/json.min.js" /> "></script>
<script src="${problem_creation_url}"></script>
<script src="${global_functions_url}"></script>

</head>
<body>
	<div id="problem">
	<form:form id="newProblemForm" name="newProblemForm" modelAttribute="problemForm" action="${urlActionProblemForm}" method="post">
	<form:input type="hidden" path="action" value="${problemForm.action}"/>
	<form:input type="hidden" path="hayVarsSel" value="${problemForm.hayVarsSel}"/>
	<form:input type="hidden" path="hayStepsSel" value="${problemForm.hayStepsSel}"/>
	<div id="initialDivTables">
		<table id="tableProblem" style="height:200px;width:45%;float:left;">
			<tr>
				<td>Enunciado Problema</td>
			</tr>
			<tr>
				<td><form:textarea id="textAreaEnunciado" name="textAreaEnunciado" path="problem.statement" rows="4" cols="50"></form:textarea></td>
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
						<form:select id="selectVariables" path="problem.numVars" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}">
						</form:select>
						<input id="inputNumVariables" name="inputNumVariables" type="hidden" value=""/>
						<c:if test="${problemForm.action == 'INICIO'}">
							<input id="buttonSetNumVariables" name="buttonSetNumVariables" type="button" onclick="createVariablesTable()" value="Generar Variables"/>
						</c:if>
				</td>
			</tr>
			<tr>
			
			</tr>
	</table>
	<c:if test="${problemForm.hayVarsSel}">
		<div id="divVariables" style="padding-left: 0">
		<table id="tableVariables">
			<c:forEach varStatus="loopVars" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}" end="${problemForm.problem.numVars-1}">
			<c:if test="${loopVars.index eq 0}">
				<c:set var="nameVarLoop" value="x"/>
			</c:if>
			<c:if test="${loopVars.index eq 1}">
				<c:set var="nameVarLoop" value="y"/>
			</c:if>
			<c:if test="${loopVars.index eq 2}">
				<c:set var="nameVarLoop" value="z"/>
			</c:if>
			<c:if test="${loopVars.index eq 3}">
				<c:set var="nameVarLoop" value="k"/>
			</c:if>
				<tr>
						<td>${nameVarLoop}=</td>
						<td><form:input id="inputInitialVar${fn:toUpperCase(nameVarLoop)}" name="inputInitialVar${fn:toUpperCase(nameVarLoop)}" path="problem.variables.${nameVarLoop}" class="${readOnlyVarsClass}" readonly="${readOnlyVars }"></form:input></td>
						
						<td><input id="inputVar${fn:toUpperCase(nameVarLoop)}" name="inputVar${fn:toUpperCase(nameVarLoop)}" readonly></input></td>
						
				</tr>
			</c:forEach>
				<tr>
						<td>Resultado=</td>
						<td><form:input id="inputVarResultado" name="inputVarResultado" path="problem.result" readonly="true"></form:input></td>
				</tr>
				
		</table>
		<br/>
		<div id="divEnunciadoVariables">
		<label>Número de Paso de Resolucion a Implementar</label>
		<label>
			<form:select id="selectResolutionSteps" name="selectResolutionSteps" path="problem.numSteps" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}">
			</form:select>
			<td>
				<input id="inputResolutionSteps" name="inputResolutionSteps" type="hidden" value=""/>
				<c:if test="${!problemForm.hayStepsSel}">
				<input type="button" onclick="createResolutionStepsTable()" value="Generar Pasos de Resolucion"/>
				</c:if>
			</td>
			</label>
		</div>
		<br/>
		</div>
	</c:if>
	<c:if test="${problemForm.hayStepsSel}">
		<div id="divResolutionSteps" >
			<table id="tableResolutionSteps">
				<c:forEach varStatus="loopSteps" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}" end="${problemForm.problem.numSteps-1}">
					<tr>
						<td>Paso Número ${loopSteps.index+1}: </td>
						<td><form:input id="inputStep${loopSteps.index+1}" name="inputStep${loopSteps.index+1}" path="problem.steps.step${loopSteps.index+1}.step"></form:input></td>
						<td><input type="button" id="stepButton${loopSteps.index+1}" value="Ejecutar Paso" onclick="readStepExecution(${loopSteps.index+1})"></input></td>
					</tr>
				</c:forEach>
				<tr>
				<td>Resolución Final: </td><td><form:input id="inputStepResultado" name="inputStepResultado" path="problem.steps.finalStep.step"></form:input></td>
				<td><input id="resolutionButton" type="button" value="Ejecutar Resolucion" onclick="ejecutarResolucion()"></input></td>
				</tr>
			</table>
			<br/>
		</div>
	</c:if>
	<div id="divConfirmarCreacionProblema" >
	<c:if test="${problemForm.readyToSaveProblem}">
		<input type="button" id="saveProblem" onclick="saveProblemSubmitJSON()" value="Guardar Problema"></input>
	</c:if>
	</div>
	</form:form>
	</div>
	
</body>
<script>

	var x;
	var y;
	var z;
	var k;
	var numVars = '${problemForm.problem.numVars}';
	var resultado;
	
	$( document ).ready(function() {
		setInitialToVariablesValues();
	});
	
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
		$('#inputStep'+numPaso).attr("readonly",true);
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
		    		  //$('#inputStepResultado').attr("disabled","disabled");
		    		  document.getElementById("newProblemForm").submit();
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
		$('#divConfirmarCreacionProblema').append('<input type="button" id="saveProblem" onclick="saveProblemSubmit()" value="Guardar Problema"></input>');
	}
	
	function saveProblemSubmit(){
		if(confirm("Desea guardar el problema?")){
			document.getElementById("newProblemForm").submit();
		}
		else{
			
		}
	}
	
	function saveProblemSubmitJSON(){
		if(confirm("Desea guardar el problema?")){
			saveProblemJSON();
		}
		else{
			
		}
	}
	
	function saveProblemJSON(){
		$.ajax({  
		     type : "POST",   
		     url : "/wolfmaths/problemRest/guardarProblemaJSON",
		     data : JSON.stringify(${problemForm.problem}),
		     contentType: "application/json",
		     success : function(response) {
		    	 
		   	 alert("Problema guardado");
		       
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });
	}
	
	
	function getValorX(){
		console.log(x);
		return x;
	}
	
	
	
	
	
</script>
</html>