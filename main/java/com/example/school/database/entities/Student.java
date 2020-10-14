package com.example.school.database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.school.utilities.interfaces.INullable;

@Entity
@Table(name = "students")
public class Student extends User implements Serializable, INullable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;
	
	@OneToMany(mappedBy = "student")
	private List<Grade> grades;
	
	@OneToMany(mappedBy = "student")
	private List<Presence> presences;
	
	@ManyToMany
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
			inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName="classId"))
	private List<Course> courses;
	
	public Student(String name, String email, String password) {
		super(name, email, password, true);
		setGrades(new ArrayList<Grade>());
		setPresences(new ArrayList<Presence>());
		setCourses(new ArrayList<Course>());
	}

	public Student() {
		super();
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Presence> getPresences() {
		return presences;
	}

	public void setPresences(List<Presence> presences) {
		this.presences = presences;
	}
}
