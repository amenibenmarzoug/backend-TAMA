package com.eniso.tama.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class Day {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Days day;

    //private String dayName;
    public Day() {

    }
	
	/*public Day(String day) {
		System.out.println(Days.LUNDI.name());
		switch (day) {
		case "LUNDI":
			this.dayName=Days.LUNDI.name();
			break;

		case "MARDI":
			this.dayName=Days.MARDI.name();
			break;
		case "MERCREDI":
			this.dayName=Days.MERCREDI.name();
			break;
		case "JEUDI":
			this.dayName=Days.JEUDI.name();
			break;
		case "VENDREDI":
			this.dayName=Days.VENDREDI.name();
			break;
		case "SAMEDI":
			this.dayName=Days.SAMEDI.name();
			break;
		case "DIMANCHE":
			this.dayName=Days.DIMANCHE.name();
			break;
		}
	}*/

	/*public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}*/

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

}
