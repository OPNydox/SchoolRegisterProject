package com.example.school.viewModels;

import com.example.school.viewModels.Interfaces.ViewModel;

public class GradeViewModel extends ViewModel {
	
	private String mark;
	
	private String className;
	
	private String studentEmail;

	private Long studentId;

	private Long courseId;

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

	public Long getCourseId() {
		return courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
