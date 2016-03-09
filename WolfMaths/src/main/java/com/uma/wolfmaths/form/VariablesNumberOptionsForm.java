package com.uma.wolfmaths.form;

import java.util.LinkedHashMap;
import java.util.Map;

public class VariablesNumberOptionsForm {
	private Map<Integer,Integer> opcionesSelect;
	
	public VariablesNumberOptionsForm(){
		this.opcionesSelect = new LinkedHashMap<Integer,Integer>();
	}

	public Map<Integer, Integer> getOpcionesSelect() {
		return opcionesSelect;
	}

	public void setOpcionesSelect(Map<Integer, Integer> opcionesSelect) {
		this.opcionesSelect = opcionesSelect;
	}
	
	

}
