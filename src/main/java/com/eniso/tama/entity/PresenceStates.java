package com.eniso.tama.entity;

public enum PresenceStates {
	PRESENT("PRESENT"),ABSENT("ABSENT"), NOTIFIEDABSENT("Absent Justifié");
	public final String label;
	 
    private PresenceStates(String label) {
        this.label = label;
    }

}
