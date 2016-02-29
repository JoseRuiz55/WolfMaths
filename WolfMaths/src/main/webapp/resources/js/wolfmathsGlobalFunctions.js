/**
 * 
 */
const API_RESULT_SEPARATOR = "&%";

function replaceSpaces(cadena){
		while(cadena.indexOf(' ') > -1){
			cadena = cadena.replace(' ','');
		}
		return cadena;
	}