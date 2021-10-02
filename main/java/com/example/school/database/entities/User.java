package com.example.school.database.entities;

import javax.persistence.*;

import com.example.school.utilities.enums.UserRole;
import com.example.school.utilities.userUtils.UserTypeUtil;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;

import com.example.school.utilities.interfaces.INullable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registry_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="isStudent", discriminatorType = DiscriminatorType.INTEGER)
public class User implements INullable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(length = 60)
	private String password;
	
	private int userType;
	
	@Transient
	private boolean isEmpty;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
	private Set<Grade> grades;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(
			name = "user_course",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
			inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName="courseID"))
	private Set<Course> courses;
	
	public User() { }

	public User(String name, String email, String password, boolean isStudent) {
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setStudent(isStudent);
		this.setGrades(new HashSet<>());
		this.setCourses(new HashSet<>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStudent() {
		return new UserTypeUtil(this.userType).isStudent();
	}

	public void setStudent(boolean isStudent) {
		this.userType = 1;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getUserType() {
		return userType;
	}

	protected void setUserType(UserRole userRole) {
		this.userType = userRole.getValue();
	}

	@Override
	public boolean isNull() {
		if (this.isEmpty ||this.getName() == null || this.getEmail() == null) {
			return true;
		}
		return false;
	}

	@Override
	public void setEmpty() {
		this.isEmpty = true;
	}

}
