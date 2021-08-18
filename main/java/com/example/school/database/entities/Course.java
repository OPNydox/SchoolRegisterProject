package com.example.school.database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.Transient;

import com.example.school.utilities.interfaces.INullable;

@Entity
@Table(name = "courses")
public class Course implements INullable, Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	
	@Column(unique = true)
	private String courseName;
	
	private String subject;
	
	private int honorarium;
	
	@Transient
	private boolean isEmpty;
	
	@ManyToMany(mappedBy = "courses")
	private Set<User> users;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "courseGrade")
	private Set<Grade> grades;

	public Course() {
		super();
	}

	public Course(String subject, String courseName, int honorarium) {
		super();
		this.setSubject(subject);
		this.setCourseName(courseName);
		this.setHonorarium(honorarium);
		this.users = new HashSet<>();
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getHonorarium() {
		return honorarium;
	}

	public void setHonorarium(int honorarium) {
		this.honorarium = honorarium;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean empty) {
		isEmpty = empty;
	}

	public Set<Student> getStudents() {
		Set<Student> courseStudents = new HashSet<>();

		users.forEach(user -> {
			if (user.isStudent()) {
				courseStudents.add((Student) user);
			}
		});
		return courseStudents;
	}

	public void addStudents(Set<User> students) {
		users.addAll(students);
	}

	public Set<Teacher> getTeachers() {
		Set<Teacher> courseTeachers = new HashSet<>();

		users.forEach(user -> {
			if (!user.isStudent()) {
				courseTeachers.add((Teacher) user);
			}
		});
		return courseTeachers;
	}

	public void setTeachers(Set<User> teachers) {
		users.addAll(teachers);
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public boolean isNull() {
		if (this.isEmpty || this.getCourseName() == null || this.getSubject() == null) {
			return true;
		}
		
		return false;
	}

	@Override
	public void setEmpty() {
		this.isEmpty = true;
		
	}
	
}
