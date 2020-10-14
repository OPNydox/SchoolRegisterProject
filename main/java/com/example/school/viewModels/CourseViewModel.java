package com.example.school.viewModels;

public class CourseViewModel extends ViewModel{

	private String id;

	private String name;

	private String subject;

	private String honorarium;

	private boolean isEmpty;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean isEmpty() {
		return this.isEmpty;
	}

	@Override
	public boolean setEmpty() {
		this.isEmpty = true;
		return true;
	}
	
	
}
