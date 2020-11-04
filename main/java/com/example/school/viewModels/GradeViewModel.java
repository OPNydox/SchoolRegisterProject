package com.example.school.viewModels;

import com.example.school.viewModels.Interfaces.ViewModel;

public class GradeViewModel extends ViewModel {
	
	private String mark;
	
	private String className;
	
	private String studentEmail;

	private String studentId;

	private String courseId;

	public GradeViewModel() { }
	
	public GradeViewModel(String mark, String className, String studentEmail) {
		super();
		this.setMark(mark);
		this.setClassName(className);
		this.setStudentEmail(studentEmail);
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}
