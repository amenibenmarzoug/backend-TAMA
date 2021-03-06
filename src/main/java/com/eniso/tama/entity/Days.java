package com.eniso.tama.entity;

public enum Days {
	DIMANCHE("DIMANCHE"), LUNDI("LUNDI"), MARDI("MARDI"), MERCREDI("MERCREDI"), JEUDI("JEUDI"), VENDREDI("VENDREDI") ,SAMEDI("SAMEDI");
	  public final String label;
		 
	    private Days(String label) {
	        this.label = label;
	    }
}
