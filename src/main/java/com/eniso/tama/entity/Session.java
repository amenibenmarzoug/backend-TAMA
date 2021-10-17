package com.eniso.tama.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Session {
  
    @Id
    @Column(name = "session_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column
    private String sessionName;
    
    @Column(name = "session_begin_date")
    private Date sessionBeginDate;
    
    @Column(name = "session_end_date")
    private Date sessionEndDate;
    
    @ManyToOne
    private ThemeDetailInstance themeDetailInstance;
    
    @ManyToOne
    private ClassRoom classRoom;
    
    @ManyToOne
    private Trainer trainer;
    
    @JsonIgnore
    @OneToOne(mappedBy = "session")
    private Event event;
    
    @JsonIgnore
   	@OneToMany(mappedBy = "session")
   	Set<Attendance> attendance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Date getSessionBeginDate() {
        return sessionBeginDate;
    }

    public void setSessionBeginDate(Date sessionBeginDate) {
        this.sessionBeginDate = sessionBeginDate;
    }

    public Date getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(Date sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public ThemeDetailInstance getThemeDetailInstance() {
        return themeDetailInstance;
    }

    public void setThemeDetailInstance(ThemeDetailInstance themeDetailInstance) {
        this.themeDetailInstance = themeDetailInstance;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

	public Set<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(Set<Attendance> attendance) {
		this.attendance =attendance;
	}

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendance == null) ? 0 : attendance.hashCode());
		result = prime * result + ((classRoom == null) ? 0 : classRoom.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sessionBeginDate == null) ? 0 : sessionBeginDate.hashCode());
		result = prime * result + ((sessionEndDate == null) ? 0 : sessionEndDate.hashCode());
		result = prime * result + ((sessionName == null) ? 0 : sessionName.hashCode());
		result = prime * result + ((themeDetailInstance == null) ? 0 : themeDetailInstance.hashCode());
		result = prime * result + ((trainer == null) ? 0 : trainer.hashCode());
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
		Session other = (Session) obj;
		if (attendance == null) {
			if (other.attendance != null)
				return false;
		} else if (!attendance.equals(other.attendance))
			return false;
		if (classRoom == null) {
			if (other.classRoom != null)
				return false;
		} else if (!classRoom.equals(other.classRoom))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sessionBeginDate == null) {
			if (other.sessionBeginDate != null)
				return false;
		} else if (!sessionBeginDate.equals(other.sessionBeginDate))
			return false;
		if (sessionEndDate == null) {
			if (other.sessionEndDate != null)
				return false;
		} else if (!sessionEndDate.equals(other.sessionEndDate))
			return false;
		if (sessionName == null) {
			if (other.sessionName != null)
				return false;
		} else if (!sessionName.equals(other.sessionName))
			return false;
		if (themeDetailInstance == null) {
			if (other.themeDetailInstance != null)
				return false;
		} else if (!themeDetailInstance.equals(other.themeDetailInstance))
			return false;
		if (trainer == null) {
			if (other.trainer != null)
				return false;
		} else if (!trainer.equals(other.trainer))
			return false;
		return true;
	}
    
    


}