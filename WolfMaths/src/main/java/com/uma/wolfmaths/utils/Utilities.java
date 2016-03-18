package com.uma.wolfmaths.utils;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.wolfmaths.form.ProblemForm;

public class Utilities {

	private static final Logger logger = LoggerFactory.getLogger(Utilities.class);
	
	public static ProblemForm inicializaFormulario(ProblemForm problemForm){
		LinkedHashMap<Integer, Integer> inicializadorVariables = new LinkedHashMap<Integer,Integer>();
		inicializadorVariables.put(1, 1);
		inicializadorVariables.put(2, 2);
		inicializadorVariables.put(3, 3);
		inicializadorVariables.put(4, 4);
		problemForm.getVariablesNumberOptionsForm().setOpcionesSelect(inicializadorVariables);
		return problemForm;
	}
}
