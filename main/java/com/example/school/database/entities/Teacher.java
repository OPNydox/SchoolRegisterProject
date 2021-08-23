package com.example.school.database.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.school.utilities.enums.UserRole;
import com.example.school.utilities.interfaces.INullable;

@Entity
@DiscriminatorValue("2")
public class Teacher extends User  {
	private double salary;

	public Teacher(String name, String email, String password, double salary) {
		super(name, email, password, false);
		setUserType(UserRole.TEACHER);
		setSalary(salary);
	}

	public Teacher() {
		super();
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
