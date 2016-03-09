/**
 * 
 */

function createVariablesTable() {
		numVars = $('#selectVariables').val();
		$('#inputNumVariables').val(numVars);
		//alert("Numero de Variables Seleccionadas: " + numVars);
		console.log("NumVars"+numVars);
		//createVarsTrs(numVars);
		addTrAction("Seleccion de "+numVars+" variables para la resoluci&oacute;n del problema");
		document.getElementById("newProblemForm").submit();
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
				html=html+'<td>'+nameVar+'=</td><td><input id="inputInitialVar'+nameVar.toUpperCase()+'" name="inputInitialVar'+nameVar.toUpperCase()+' path="problem.variables.'+nameVar.toLowerCase()+'"></input></td><td><input id="inputVar'+nameVar.toUpperCase()+'" name="inputVar'+nameVar.toUpperCase()+'" readonly></input></td>';
				html=html+'</tr>';
			}
		//VariableResultado
		html=html+'<tr>';
		html=html+'<td>Resultado=</td><td><input id="inputVarResultado" name="inputVarResultado" path="problem.result" readonly></input></td>';
		html=html+'</tr>';
		//FinVariableResultado
		console.log(html);
		$('#tableVariables').append(html);
		html='<label>N&uacute;mero de Paso de Resolucion a Implementar</label>';
		html ='<label>'+getSelectButtonResolutionSteps()+'</label>';
		console.log(html);
		$('#divEnunciadoVariables').empty();
		$('#divEnunciadoVariables').append(html);
		
		//$(html).appendTo('#trVariables');
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
				html=html+'<td>Paso N&uacute;mero: '+numStep+'</td><td><input id="inputStep'+numStep.toUpperCase()+'" name="inputStep'+numStep.toUpperCase()+'" path="problem.steps.step'+numStep.toUpperCase()+'.step"></input></td><td><input type="button" id="stepButton'+numStep+'" value="Ejecutar Paso" onclick="readStepExecution('+numStep+')"></input></td>';
				html=html+'</tr>';
			}
		
		html=html+'<tr>';
		html=html+'<td>Resolución Final: </td><td><input id="inputStepResultado" name="inputStepResultado" path="problem.steps.finalStep.step"></input></td><td><input id="resolutionButton" type="button" value="Ejecutar Resolucion" onclick="ejecutarResolucion()"></input></td>';
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
	
	function createResolutionStepsTable()
	{
		setReadOnlyInitialVariables();
		setInitialToVariablesValues();
		setReadOnlyVariables();
		var numSteps = $('#selectResolutionSteps').val();
		//Asignamos el numero de pasos a resolver
		$('#inputResolutionSteps').val($('#selectResolutionSteps').val());
		
		//alert("Numero de Variables Seleccionadas: " + numVars);
		//createStepsTrs(numSteps);
		addTrAction("Seleccion de "+numSteps+" pasos para la resoluci&oacute;n del problema");
		document.getElementById("newProblemForm").submit();
		
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
	
	
	
	function getSelectButtonResolutionSteps()
	{
		var selectButtonHtml = '';
		selectButtonHtml = selectButtonHtml+
		'<form:select id="selectResolutionSteps" name="selectResolutionSteps" path="problem.numSteps">'+
		'<option	value="1">1</option>'+
		'<option value="2">2</option>'+
		'<option value="3">3</option>'+
		'<option value="4">4</option>'+
		'</form:select><td>'+
		'<input id="inputResolutionSteps" name="inputResolutionSteps" type="hidden" value=""/>'+
		'<input type="button" onclick="createResolutionStepsTable()" value="Generar Pasos de Resolucion"/></td>';
		console.log(selectButtonHtml);
		return selectButtonHtml;
	}
	
	
	
	function setVariablesValues(){
		console.log("NumVars"+numVars);
		switch(parseInt(numVars)) {
	    case 0:
	        return alert("Error, deja de tocar el javascript");
	        break;
	    case 1:
	        x = replaceSpaces($('#inputVarX').val());
	        break;
	    case 2:
	    	x = replaceSpaces($('#inputVarX').val());
	    	y = replaceSpaces($('#inputVarY').val());
	        break;
	    case 3:
	    	x = replaceSpaces($('#inputVarX').val());
	    	y = replaceSpaces($('#inputVarY').val());
	    	z = replaceSpaces($('#inputVarZ').val());
	        break;
	    case 4:
	    	x = replaceSpaces($('#inputVarX').val());
	    	y = replaceSpaces($('#inputVarY').val());
	    	z = replaceSpaces($('#inputVarZ').val());
	    	k = replaceSpaces($('#inputVarK').val());
	        break;
	    default:
	        return "ERROR";
		}
	}
	
	function setInitialToVariablesValues(){
		console.log("NumVars"+numVars);
		switch(parseInt(numVars)) {
	    case 1:
	    	x = replaceSpaces($('#inputInitialVarX').val());
	    	$('#inputVarX').val($('#inputInitialVarX').val());
	        break;
	    case 2:
	    	x = replaceSpaces($('#inputInitialVarX').val());
	    	$('#inputVarX').val($('#inputInitialVarX').val());
	    	y = replaceSpaces($('#inputInitialVarY').val());
	    	$('#inputVarY').val($('#inputInitialVarY').val());
	        break;
	    case 3:
	    	x = replaceSpaces($('#inputInitialVarX').val());
	    	$('#inputVarX').val($('#inputInitialVarX').val());
	    	y = replaceSpaces($('#inputInitialVarY').val());
	    	$('#inputVarY').val($('#inputInitialVarY').val());
	    	z = replaceSpaces($('#inputInitialVarZ').val());
	    	$('#inputVarZ').val($('#inputInitialVarZ').val());
	        break;
	    case 4:
	    	x = replaceSpaces($('#inputInitialVarX').val());
	    	$('#inputVarX').val($('#inputInitialVarX').val());
	    	y = replaceSpaces($('#inputInitialVarY').val());
	    	$('#inputVarY').val($('#inputInitialVarY').val());
	    	z = replaceSpaces($('#inputInitialVarZ').val());
	    	$('#inputVarZ').val($('#inputInitialVarZ').val());
	    	k = replaceSpaces($('#inputInitialVarK').val());
	    	$('#inputVarK').val($('#inputInitialVarK').val());
	        break;
	    default:
	        return "ERROR";
		}
	}
	
	
	function setReadOnlyVariables(){
		console.log("NumVars"+numVars);
		switch(parseInt(numVars)) {
	    case 0:
	        return alert("Error, deja de tocar el javascript");
	        break;
	    case 1:
	        $('#inputVarX').attr('readonly',true);
	        break;
	    case 2:
	    	$('#inputVarX').attr('readonly',true);
	    	$('#inputVarY').attr('readonly',true);
	        break;
	    case 3:
	    	$('#inputVarX').attr('readonly',true);
	    	$('#inputVarY').attr('readonly',true);
	    	$('#inputVarZ').attr('readonly',true);
	        break;
	    case 4:
	    	$('#inputVarX').attr('readonly',true);
	    	$('#inputVarY').attr('readonly',true);
	    	$('#inputVarZ').attr('readonly',true);
	    	$('#inputVarK').attr('readonly',true);
	        break;
	    default:
	        return "ERROR";
		}
	}
	
	function setReadOnlyInitialVariables(){
		console.log("NumVars"+numVars);
		switch(parseInt(numVars)) {
	    case 0:
	        return alert("Error, deja de tocar el javascript");
	        break;
	    case 1:
	        $('#inputInitialVarX').attr('readonly',true);
	        break;
	    case 2:
	    	$('#inputInitialVarX').attr('readonly',true);
	    	$('#inputInitialVarY').attr('readonly',true);
	        break;
	    case 3:
	    	$('#inputInitialVarX').attr('readonly',true);
	    	$('#inputInitialVarY').attr('readonly',true);
	    	$('#inputInitialVarZ').attr('readonly',true);
	        break;
	    case 4:
	    	$('#inputInitialVarX').attr('readonly',true);
	    	$('#inputInitialVarY').attr('readonly',true);
	    	$('#inputInitialVarZ').attr('readonly',true);
	    	$('#inputInitialVarK').attr('readonly',true);
	        break;
	    default:
	        return "ERROR";
		}
	}
	
	function getVariablesValueToInputStep(inputStep){
		/*EXPLICACIÓN IF*/
		//En el if compruebo si se corresponde con una asignación a alguna variable
		//en caso de que no se corresponda con una asignación le sustituyo el valor a la variable (x,y,z,k) y vuelvo a llamar a la función ya que puede que se asigne el valor de la variable varias veces
		//la comprobación de que no es una asignación se hace encontrando la posicion de la variable y comprobando si la posición siguiente es un '='
		
		/*EXPLICACIÓN ELSE IF*/
		//En caso de que exista una asignación es posible que en la cadena posterior de la llamada se haga uso de la variable que se esta asignando (i.e: x=2x)
		//en este caso concadeno el valor de la asignación con la llamada quitando la cadena (ya la asignación debe de ser la "primera" sentencia)
		
		
		if(inputStep.indexOf('x') > -1 && inputStep.charAt((inputStep.indexOf('x'))+1)!= '='){
			inputStep = inputStep.replace('x','('+x+')');
			inputStep = getVariablesValueToInputStep(inputStep);
		}else if(inputStep.indexOf('x=') > -1){
			inputStep1 = getVariablesValueToInputStep(inputStep.split('x=')[1]);
			inputStep = 'x='+inputStep1;
		}
		if(inputStep.indexOf('y') > -1 && inputStep.charAt((inputStep.indexOf('y'))+1)!= '='){
			inputStep = inputStep.replace('y','('+y+')');
			inputStep = getVariablesValueToInputStep(inputStep);
		}else if(inputStep.indexOf('y=') > -1){
			inputStep1 = getVariablesValueToInputStep(inputStep.split('y=')[1]);
			inputStep = 'y='+inputStep1;
		}
		if(inputStep.indexOf('z') > -1 && inputStep.charAt((inputStep.indexOf('z'))+1)!= '='){
			inputStep = inputStep.replace('z','('+z+')');
			inputStep = getVariablesValueToInputStep(inputStep);
		}else if(inputStep.indexOf('z=') > -1){
			inputStep1 = getVariablesValueToInputStep(inputStep.split('z=')[1]);
			inputStep = 'z='+inputStep1;
		}
		if(inputStep.indexOf('k') > -1 && inputStep.charAt((inputStep.indexOf('k'))+1)!= '='){
			inputStep = inputStep.replace('k','('+k+')');
			inputStep = getVariablesValueToInputStep(inputStep);
		}else if(inputStep.indexOf('k=') > -1){
			inputStep1 = getVariablesValueToInputStep(inputStep.split('k=')[1]);
			inputStep = 'k='+inputStep1;
		}
		
		return inputStep;
	}
	
	function getVarToAssign(inputStep){
		if(inputStep.indexOf('x=') > -1){
			return 'x=';
		}
		else if(inputStep.indexOf('y=') > -1){
			return 'y=';
		}
		else if(inputStep.indexOf('z=') > -1){
			return 'z=';
		}
		else if(inputStep.indexOf('k=') > -1){
			return 'k=';
		}
		else{
			return "";
		}
		
	}
	
	function assignValueToASingleVar(varToAssign,valueToAssign){
		if(varToAssign=='x='){
			$('#inputVarX').val(valueToAssign);
		}
		else if(varToAssign=='y='){
			$('#inputVarY').val(valueToAssign);
		}
		else if(varToAssign=='z='){
			$('#inputVarZ').val(valueToAssign);
		}
		else if(varToAssign=='k='){
			$('#inputVarK').val(valueToAssign);
		}
		else{
			alert("ERROR en assigValueToASingleVar");
			console.log("ERROR en assigValueToASingleVar");
		}
		
	}
	
	
	function addTrAction(accion)
	{
		$('#tableActions').append('<tr><td>'+accion+'</tr></td>');
	}