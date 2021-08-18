package com.example.school.database.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.Transient;

import com.example.school.utilities.interfaces.INullable;

@Entity
@Table(name = "presences")
public class Presence implements INullable, Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long presenceId;
	
	@ManyToOne()
	@JoinColumn(name = "userId")
	private User student;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Course presenceClass;

	@Transient
	private boolean isEmpty;
	
	public Presence() {
		super();
	}

	public Presence(Student student, Course presenceClass) {
		super();
		this.student = student;
		this.presenceClass = presenceClass;
	}
	
	public long getPresenceId() {
		return presenceId;
	}

	public void setPresenceId(long presenceId) {
		this.presenceId = presenceId;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Course getPresenceClass() {
		return presenceClass;
	}

	public void setPresenceClass(Course presenceClass) {
		this.presenceClass = presenceClass;
	}

	@Override
	public boolean isNull() {
		if (this.isEmpty || this.getStudent() == null || this.getClass() == null) {
			return true;
		}
		return false;
	}

	@Override
	public void setEmpty() {
		this.isEmpty = true;
	}
	
	
}
