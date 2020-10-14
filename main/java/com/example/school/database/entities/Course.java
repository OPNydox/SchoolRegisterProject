package com.example.school.database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.example.school.utilities.interfaces.INullable;

@Entity
@Table(name = "classes")
public class Course implements INullable, Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classId;
	
	@Column(unique = true)
	private String courseName;
	
	private String subject;
	
	private int honorarium;
	
	@Transient
	private boolean isEmpty;
	
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
	
	@ManyToMany(mappedBy = "courses")
	private List<Teacher> teachers;
	
	@OneToMany(mappedBy = "classGrade")
	private List<Grade> grades;

	public Course() {
		super();
	}

	public Course(String subject, String courseName, int honorarium) {
		super();
		this.setSubject(subject);
		this.setCourseName(courseName);
		this.setHonorarium(honorarium);
		this.setStudents(new ArrayList<Student>());
		this.setTeachers(new ArrayList<Teacher>());
		this.setGrades(new ArrayList<Grade>());
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
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

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	} 
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Grade> grades) {
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
