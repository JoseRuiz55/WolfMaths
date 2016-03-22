<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true" %>
<html>
<jsp:include page="/WEB-INF/views/header.jsp" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario de Creación de Problemas</title>

<spring:url value="/problem/createProblemForm" var="url_newProblem"/>
<spring:url value="/problem/selVarsNumber" var="url_selVarsNumber"/>
<spring:url value="/problem/selStepsNumber" var="url_selStepsNumber"/>
<spring:url value="/problem/readyToSaveProblem" var="url_readyToSaveProblem"/>
<spring:url value="/problem/guardarProblema" var="url_guardarProblema"/>
<spring:url value="/problem/resolverProblema" var="url_resolverProblema"/>
<spring:url value="/profesor/obtenerProblemasProfesor" var="url_ObtenerProblemasProfesor"/>
<spring:url value="/problemRest/guardarProblemaJSON" var="url_guardarProblemaJSON"/>
<spring:url value="/problemRest/resolverProblemaJSON" var="url_resolverProblemaJSON"/>

<c:set var="INICIO" value="INICIO"/>
<c:set var="VARSSELECCIONADAS" value="VARSSELECCIONADAS"/>
<c:set var="STEPSSELECCIONADOS" value="STEPSSELECCIONADOS"/>
<c:set var="READYTOSAVEPROBLEM" value="READYTOSAVEPROBLEM"/>


<c:set var="readOnlyVarsClass" value=""/>
<c:set var="readOnlyVars" value="false"/>

<c:set var="urlActionProblemForm" value =""/>
<c:if test="${problemForm.action == 'INICIO'}">
	<c:set var="urlActionProblemForm" value="${url_selVarsNumber}" />
</c:if>
<c:if test="${problemForm.action == 'VARSSELECCIONADAS'}">
	<c:set var="hiddenVisibility" value="hidden"/>
	<c:set var="urlActionProblemForm" value="${url_selStepsNumber}" />
</c:if>
<c:if test="${problemForm.action == 'STEPSSELECCIONADOS'}">
	<c:set var="hiddenVisibility" value="hidden"/>
	<c:set var="urlActionProblemForm" value="${url_readyToSaveProblem}" />
	<c:set var="readOnlyVars" value="true"/>
	<c:set var="readOnlyVarsClass" value="readonlyVars"/>
	
</c:if>

<c:if test="${problemForm.action == 'READYTOSAVEPROBLEM'}">
	<c:set var="hiddenVisibility" value="hidden"/>
	<c:set var="urlActionProblemForm" value="${url_guardarProblema}" />
	<c:set var="readOnlyVars" value="true"/>
	<c:set var="readOnlyVarsClass" value="readonlyVars"/>
</c:if>

<c:if test="${problemForm.action == 'READYTORESOLVEPROBLEM'}">
	<c:set var="hiddenVisibility" value="hidden"/>
	<c:set var="urlActionProblemForm" value="${url_resolverProblema}" />
	<c:set var="readOnlyVars" value="true"/>
	<c:set var="readOnlyVarsClass" value="readonlyVars"/>
</c:if>

<c:if test="${problemForm.action == 'READYTOGRADEPROBLEM'}">
	<c:set var="hiddenVisibility" value="hidden"/>
	<c:set var="urlActionProblemForm" value="${url_ObtenerProblemasProfesor}" />
	<c:set var="readOnlyVars" value="true"/>
	<c:set var="readOnlyVarsClass" value="readonlyVars"/>
</c:if>

</head>
<body>
	<div id="problem">
	<form:form id="newProblemForm" name="newProblemForm" modelAttribute="problemForm" action="${urlActionProblemForm}" method="post">
	<form:input type="hidden" path="action" value="${problemForm.action}"/>
	<form:input type="hidden" path="hayVarsSel" value="${problemForm.hayVarsSel}"/>
	<form:input type="hidden" path="hayStepsSel" value="${problemForm.hayStepsSel}"/>
	<form:input type="hidden" path="problem.idProblemResolucion" value="${problemForm.problem.idProblemResolucion}"/>
	<!-- <form:input type="hidden" path="problem.profesor" value="${problemForm.problem.profesor}"/>-->
	<br>
	<br>
	<div id="initialDivTables" class="well divInitialTables">
		<table id="tableProblem" style="height:200px;width:45%;float:left;">
			<tr>
				<td><h1>Enunciado Problema</h1></td>
			</tr>
			<tr>
				<td><form:textarea id="textAreaEnunciado" name="textAreaEnunciado" path="problem.statement" rows="4" cols="50" class="form-control textAreaEnunciadoProblema"></form:textarea></td>
			</tr>
			
		</table>
		<div id="divTableActions" style="overflow:scroll;height:200px;width:45%;float:rigth;display:none;">
		<table id="tableActions" >
			<tr>
				<td>Acciones realizadas</td>
			</tr>
		</table>
		</div>
	</div>
	<table id="tableSelectNumVariables" class="tableSelectNumVars">
	<tr>
				<td><h1>Número de Variables</h1></td>
			</tr>
			<tr>
				<td>
						<c:if test="${problemForm.action == 'INICIO'}">
						<form:select id="selectVariables" path="problem.numVars" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}" style="" class="form-control selectNumVars ">
						</form:select>
						</c:if>
						<c:if test="${problemForm.action != 'INICIO'}">
						<form:input type="hidden" id="selectVariables" path="problem.numVars" style="" class="form-control selectNumVars "/>
						<select id="selectVariables" class="form-control selectNumVars " disabled="true">
						<option selected="selected">${problemForm.problem.numVars }</option>
						</select>
						</c:if>
						<br>
						<input id="inputNumVariables" name="inputNumVariables" type="hidden" value=""/>
						<c:if test="${problemForm.action == 'INICIO'}">
							<a class="btn btn-info btn-lg" onclick="createVariablesTable()"><span class="glyphicon glyphicon-list"></span> Generar Variables</a>
						</c:if>
				</td>
			</tr>
			<tr>
			
			</tr>
	</table>
	<c:if test="${problemForm.hayVarsSel}">
		<div id="divVariables" style="padding-left: 0" >
		<table id="tableVariables" class="well tablesProblemForm">
		<thead>
			<tr id="trNombreVariable">
			<td><label class="col-sm-2 form-control-label marginForm labelsThVariablesTable">Variable</label></td>
			<td><label class="col-sm-2 form-control-label marginForm labelsThVariablesTable">Valor Inicial</label></td>
			<td class="separatorTd"></td>
			<td><label class="col-sm-2 form-control-label marginForm labelsThVariablesTable">Valor en paso Actual</label></td>
			</tr>
		</thead>
		</tbody>
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
						<td><label class="col-sm-2 form-control-label">       ${fn:toUpperCase(nameVarLoop)}</label></td>
						<td><form:input id="inputInitialVar${fn:toUpperCase(nameVarLoop)}" name="inputInitialVar${fn:toUpperCase(nameVarLoop)}" path="problem.variables.${nameVarLoop}" class="${readOnlyVarsClass} form-control marginForm inputSteps" readonly="${readOnlyVars }"></form:input></td>
						<td class="separatorTd"></td>
						<td><input id="inputVar${fn:toUpperCase(nameVarLoop)}" name="inputVar${fn:toUpperCase(nameVarLoop)}" class="form-control marginForm inputSteps" readonly></input></td>
						
				</tr>
				<tr class="emptyTrProblemForm"></tr>
				<br>
			</c:forEach>
			<br>
				<tr>
						<td><label class="col-sm-2 form-control-label">Resultado=</label></td>
						<td><form:input id="inputVarResultado" name="inputVarResultado" path="problem.result" readonly="true" class="form-control marginForm inputSteps"></form:input></td>
				</tr>
				</tbody>
		</table>
		<br/>
		<div id="divEnunciadoVariables">
		<h1>Número de Paso de Resolucion a Implementar</h1>
		<br>
		<label>
			<c:if test="${!problemForm.hayStepsSel}">
			<form:select id="selectResolutionSteps" name="selectResolutionSteps" path="problem.numSteps" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}" class="form-control selectNumVars" >
			</form:select>
			</c:if>
			<c:if test="${problemForm.hayStepsSel}">
			<form:input type="hidden" path="problem.numSteps"/>
			<select id="selectResolutionSteps" name="selectResolutionSteps" class="form-control selectNumVars" disabled="true">
				<option value="${problemForm.problem.numSteps }" selected="selected">${problemForm.problem.numSteps }</option>
			</select>
			<br>
			</c:if>
				<input id="inputResolutionSteps" name="inputResolutionSteps" type="hidden" value="" />
				<c:if test="${!problemForm.hayStepsSel}">
				<a onclick="createResolutionStepsTable()" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-tasks"></span> Generar Pasos de Resolucion</a>
				</c:if>
			</label>
		</div>
		<br/>
		</div>
	</c:if>
	<c:if test="${problemForm.hayStepsSel}">
		<div id="divResolutionSteps" >
			<table id="tableResolutionSteps" class="well tablesProblemForm">
				<thead>
				<tr id="trNombreVariable">
				<td><label class="col-sm-2 form-control-label marginForm labelsThVariablesTable"></label></td>
				<td><label class="col-sm-2 form-control-label marginForm labelsThVariablesTable">Sentencia</label></td>
				<td><label class="col-sm-2 form-control-label marginForm labelsThVariablesTable">Acción</label></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach varStatus="loopSteps" items="${problemForm.variablesNumberOptionsForm.opcionesSelect}" end="${problemForm.problem.numSteps-1}">
					<tr>
						<td><label class="col-sm-2 form-control-label labelsThVariablesTable">Paso Número ${loopSteps.index+1}: </label></td>
						<td><form:input id="inputStep${loopSteps.index+1}" name="inputStep${loopSteps.index+1}" path="problem.steps.step${loopSteps.index+1}.step" class="form-control inputSteps"></form:input></td>
						<c:if test="${not problemForm.readyToGradeProblem}">
						<td><a id="stepButton${loopSteps.index+1}" onclick="readStepExecution(${loopSteps.index+1})" class="btn btn-warning btn-lg marginForm inputSteps"><span class="glyphicon glyphicon-console"></span> Ejecutar Paso</a></td>
						</c:if>
					</tr>
					<tr class="emptyTrProblemForm"></tr>
					<br>
				</c:forEach>
				<tr>
				<td><label class="col-sm-2 form-control-label labelsThVariablesTable">Resolución Final: </label></td><td><form:input id="inputStepResultado" name="inputStepResultado" path="problem.steps.finalStep.step" class="form-control inputSteps"></form:input></td>
				<c:if test="${not problemForm.readyToGradeProblem}">
				<td>
				<a onclick="ejecutarResolucion()" class="btn btn-warning btn-lg marginForm inputSteps"><span class="glyphicon glyphicon-circle-arrow-right"></span> Ejecutar Resolucion</a>
				</td>
				
				</c:if>
				<br>
				</tr>
				</tbody>
			</table>
			<br/>
		</div>
	</c:if>
	<div id="divConfirmarCreacionProblema" >
	<c:if test="${problemForm.readyToSaveProblem}">
		<br>
		<br>
		<br>
		<a onclick="saveProblemSubmitJSON()" class="btn btn-success btn-lg"><span class="glyphicon glyphicon-save"></span> Guardar Problema</a>
		<input type="button" id="saveProblem" onclick="saveProblemSubmitJSON()" value="Guardar Problema" class="btn btn-secondary"></input>
	</c:if>
	</div>
	
	<div id="divConfirmarResolucionProblema" >
	<c:if test="${problemForm.readyToResolveProblem}">
		<br>
		<br>
		<br>
		<a onclick="resolveProblemSubmitJSON()" class="btn btn-success btn-lg"><span class="glyphicon glyphicon-circle-arrow-right"></span> Enviar Solucion</a>
		<input type="button" id="resolveProblem" onclick="resolveProblemSubmitJSON()" value="Resolver Problema" class="btn btn-secondary"></input>
	</c:if>
	</div>
	<div id="divNotaProblema" >
	<c:if test="${problemForm.readyToGradeProblem}">
		<input type="hidden" id="idProblemResolucion" name="idProblemResolucion" value="${problemForm.problem.idProblemResolucion}" class="btn btn-secondary">
		<h1>Evaluar Problema</h1>
		<form>
		  <fieldset class="form-group">
		    <label for="formGroupExampleInput">Nota Problema</label>
		    <input id="idNotaInput" name="idNotaInput" class="form-control marginForm inputSteps"></input>
		  </fieldset>
		  <fieldset class="form-group">
		    <label for="formGroupExampleInput2">Comentario Problema</label>
		    <input id="idComentarioNotaInput" name="idComentarioNotaInput" class="form-control marginForm inputSteps"></input>
		  </fieldset>
		  <fieldset class="form-group">
		  <a onclick="gradeProblemSubmitJSON()" class="btn btn-success btn-lg"><span class="glyphicon glyphicon-book"></span> Guardar Corrección</a>
		  <input type="button" id="saveProblem" onclick="gradeProblemSubmitJSON()" value="Corregir Problema" class="btn btn-secondary"></input>
		  </fieldset>
		</form>
	</c:if>
	</div>
	</form:form>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
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
	
	function resolveProblemSubmitJSON(){
		if(confirm("Desea guardar la resolucion del problema?")){
			resolveProblemJSON();
		}
		else{
			
		}
	}
	
	function gradeProblemSubmitJSON(){
		if(confirm("Desea corregir el problema asignandole la nota?")){
			gradeProblemJSON();
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
	
	function resolveProblemJSON(){
		$.ajax({  
		     type : "POST",   
		     url : "/wolfmaths/problemRest/resolverProblemaJSON",
		     data : JSON.stringify(${problemForm.problem}),
		     contentType: "application/json",
		     success : function(response) {
		    	 
		   	 alert("Problema resuelto");
		       
		     },  
		     error : function(e) {  
		      alert('Error: ' + e);   
		     }  
		    });
	}
	
	function gradeProblemJSON(){
		$.ajax({  
		     type : "POST",   
		     url : "/wolfmaths/problemRest/evaluarProblemaJSON",
		     data : "{'idProblemResolucion':'"+$('#idProblemResolucion').val()+"','nota':'"+$('#idNotaInput').val()+"','comentario':'"+$('#idComentarioNotaInput').val()+"'}",
		     contentType: "application/json",
		     success : function(response) {
		    	 
		     document.getElementById("newProblemForm").submit();
		       
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