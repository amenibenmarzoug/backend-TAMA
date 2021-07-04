package com.eniso.tama.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "event")
public class Event {
	@Id
	@Column(name = "event_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//@NotNull
	private String title;


	private Date start;


	private Date end;
	

	private String colorPrimary;
	

	private String colorSecondary;
	
	@Column(name = "beforeStart",columnDefinition = "boolean default true")
	private boolean resizebeforeStart;
	
	@Column(name = "afterEnd",columnDefinition = "boolean default true")
	private boolean resizeafterEnd;
	
	@Column(name = "draggable")
	private boolean draggable;
	
	

	//@JsonIgnore

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private Session session;


    public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}

	public Long getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public Date getStart() {
		return start;
	}


	public Date getEnd() {
		return end;
	}


	public String getColorPrimary() {
		return colorPrimary;
	}


	public String getColorSecondary() {
		return colorSecondary;
	}


	public boolean isResizebeforeStart() {
		return resizebeforeStart;
	}


	public boolean isResizeafterEnd() {
		return resizeafterEnd;
	}


	public boolean isDraggable() {
		return draggable;
	}



	


	public void setId(Long id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	public void setColorPrimary(String colorPrimary) {
		this.colorPrimary = colorPrimary;
	}


	public void setColorSecondary(String colorSecondary) {
		this.colorSecondary = colorSecondary;
	}


	public void setResizebeforeStart(boolean resizebeforeStart) {
		this.resizebeforeStart = resizebeforeStart;
	}


	public void setResizeafterEnd(boolean resizeafterEnd) {
		this.resizeafterEnd = resizeafterEnd;
	}


	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}


}