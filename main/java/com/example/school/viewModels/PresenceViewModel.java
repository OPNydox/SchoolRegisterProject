package com.example.school.viewModels;

public class PresenceViewModel extends ViewModel{
	
	private String presenceClass;
	
	private String studentEmail;

	public PresenceViewModel() { }

	public PresenceViewModel(String presenceClass, String studentEmail) {
		super();
		this.setPresenceClass(presenceClass);
		this.setStudentEmail(studentEmail);
	}

	public String getPresenceClass() {
		return presenceClass;
	}

	public void setPresenceClass(String presenceClass) {
		this.presenceClass = presenceClass;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
