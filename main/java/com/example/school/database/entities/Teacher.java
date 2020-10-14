package com.example.school.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.school.utilities.interfaces.INullable;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "teachers")
public class Teacher extends User  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long teacherId;
	
	private double salary;
	
	@ManyToMany
	@JoinTable(name="teacher_course",
			joinColumns = @JoinColumn(name = "teacher_id"),
			inverseJoinColumns=@JoinColumn(name = "course_id"))
	private List<Course> courses;

	

	public Teacher(String name, String email, String password, double salary) {
		super(name, email, password, false);
		setSalary(salary);
		setCourses(new ArrayList<Course>());
	}

	public Teacher() {
		super();
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
