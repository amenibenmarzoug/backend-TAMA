package com.eniso.tama.entity;

public enum AttendanceStates {

	PRESENT("Présent"),ABSENT("ABSENT"), NOTIFIEDABSENT("Absent Justifié");
	public final String label;
	 
    private AttendanceStates(String label) {
        this.label = label;

}
}
