package com.example.school.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.example.school.utilities.interfaces.INullable;

@Entity
@Table(name = "grades")
public class Grade implements INullable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gradeId;
	
	private double mark;
	
	@Transient
	private boolean isEmpty;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course classGrade;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Grade() {
		super();
	}

	public Grade(double mark, Course classGrade, Student student) {
		super();
		setMark(mark);
		setClassGrade(classGrade);
		setStudent(student);
	}

	public long getGradeId() {
		return gradeId;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public Course getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(Course classGrade) {
		this.classGrade = classGrade;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public Student getStudent() {
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
