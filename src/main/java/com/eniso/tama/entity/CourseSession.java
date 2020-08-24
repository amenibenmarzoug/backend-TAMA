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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="courseSession")
public class CourseSession {
	@Id
    @Column(name = "course_session_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Column(name = "courseSessionName")
	private String courseSessionName;
	
	
	@Column(name = "courseSession_begin_date")
	private Date courseSessionBeginDate;
	
	@Column(name = "courseSession_end_date")
	private Date courseSessionEndDate;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseSessionName() {
		return courseSessionName;
	}

	public void setCourseSessionName(String courseSessionName) {
		this.courseSessionName = courseSessionName;
	}

	public Date getCourseSessionBeginDate() {
		return courseSessionBeginDate;
	}

	public void setCourseSessionBeginDate(Date courseSessionBeginDate) {
		this.courseSessionBeginDate = courseSessionBeginDate;
	}

	public Date getCourseSessionEndDate() {
		return courseSessionEndDate;
	}

	public void setCourseSessionEndDate(Date courseSessionEndDate) {
		this.courseSessionEndDate = courseSessionEndDate;
	}
	
	@ManyToOne
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	@ManyToOne
	private ClassRoom classRoom;

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	
    @OneToMany(mappedBy = "courseSession")
    Set<CourseSessionParticipant> courseSessionParticipant;

	public Set<CourseSessionParticipant> getCourseSessionParticipant() {
		return courseSessionParticipant;
	}

	public void setCourseSessionParticipant(Set<CourseSessionParticipant> courseSessionParticipant) {
		this.courseSessionParticipant = courseSessionParticipant;
	}
	
    @OneToMany(mappedBy = "courseSession")
    Set<TrainerDisponibility> trainerDisponibility;

	public Set<TrainerDisponibility> getTrainerDisponibility() {
		return trainerDisponibility;
	}

	public void setTrainerDisponibility(Set<TrainerDisponibility> trainerDisponibility) {
		this.trainerDisponibility = trainerDisponibility;
	}
    
	//public CourseSession() {}
    
}
