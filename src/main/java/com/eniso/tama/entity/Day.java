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

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

}
