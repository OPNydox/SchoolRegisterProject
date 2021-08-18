package com.example.school.database.entities;

import javax.persistence.*;

import org.springframework.data.annotation.Transient;

import com.example.school.utilities.interfaces.INullable;

@Entity
@Table(name = "register_grades")
public class Grade implements INullable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gradeId;
	
	private double mark;
	
	@Transient
	private boolean isEmpty;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	private Course courseGrade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private User student;

	public Grade() {
		super();
	}

	public Grade(double mark, Course classGrade, Student student) {
		super();
		setMark(mark);
		setCourseGrade(classGrade);
		setStudent(student);
	}

	public long getGradeId() {
		return gradeId;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public Course getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(Course courseGrade) {
		this.courseGrade = courseGrade;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public boolean isNull() {
		if (this.isEmpty) {
			return false;
		}
		
		return true;
	}

	@Override
	public void setEmpty() {
		this.isEmpty = true;	
	}
	
	
	
}
