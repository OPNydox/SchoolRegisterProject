package com.example.school.viewModels;

import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

import java.util.HashSet;
import java.util.Set;

public class CourseViewModel extends ViewModel {

	private Long id;

	private String name;

	private String subject;

	private String honorarium;

	private Set<GradeViewModel> grades = new HashSet<>();

	private Set<UserViewModel> students = new HashSet<>();

	private Set<UserViewModel> teachers = new HashSet<>();

	public CourseViewModel() {}
	
	public CourseViewModel(String name, String subject, String honorarium) {
		super();
		this.setName(name);
		this.setSubject(subject);
		this.setHonorarium(honorarium);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHonorarium() {
		return honorarium;
	}

	public void setHonorarium(String honorarium) {
		this.honorarium = honorarium;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<GradeViewModel> getGrades() {
		return grades;
	}

	public void setGrades(Set<GradeViewModel> grades) {
		this.grades = grades;
	}

	public Set<UserViewModel> getStudents() {
		return students;
	}

	public void setStudents(Set<UserViewModel> students) {
		this.students = students;
	}

	public Set<UserViewModel> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<UserViewModel> teachers) {
		this.teachers = teachers;
	}
}
