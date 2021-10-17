package com.eniso.tama.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Event {
	
    @Id
    @Column(name = "event_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Date start;

    @Column
    private Date end;

    @Column
    private String colorPrimary;

    @Column
    private String colorSecondary;

    @Column(name = "beforeStart",columnDefinition = "boolean default true")
    private boolean resizebeforeStart;

    @Column(name = "afterEnd",columnDefinition = "boolean default true")
    private boolean resizeafterEnd;


    @Column
    private boolean freeDay;

    @Column(columnDefinition = "boolean default true")
    private boolean draggable;


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

    public boolean isFreeDay() {
        return freeDay;
    }

    public void setFreeDay(boolean freeDay) {
        this.freeDay = freeDay;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colorPrimary == null) ? 0 : colorPrimary.hashCode());
		result = prime * result + ((colorSecondary == null) ? 0 : colorSecondary.hashCode());
		result = prime * result + (draggable ? 1231 : 1237);
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + (freeDay ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (resizeafterEnd ? 1231 : 1237);
		result = prime * result + (resizebeforeStart ? 1231 : 1237);
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (colorPrimary == null) {
			if (other.colorPrimary != null)
				return false;
		} else if (!colorPrimary.equals(other.colorPrimary))
			return false;
		if (colorSecondary == null) {
			if (other.colorSecondary != null)
				return false;
		} else if (!colorSecondary.equals(other.colorSecondary))
			return false;
		if (draggable != other.draggable)
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (freeDay != other.freeDay)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (resizeafterEnd != other.resizeafterEnd)
			return false;
		if (resizebeforeStart != other.resizebeforeStart)
			return false;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
    
    





}