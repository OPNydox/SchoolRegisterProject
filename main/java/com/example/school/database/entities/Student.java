package com.example.school.database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.school.utilities.interfaces.INullable;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Embeddable
@DiscriminatorValue("1")
public class Student extends User implements Serializable, INullable {

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
	private Set<Presence> presences;
	
	public Student(String name, String email, String password) {
		super(name, email, password, true);
		setPresences(new HashSet<>());
	}

	public Student() {
		super();
	}

	public Set<Presence> getPresences() {
		return presences;
	}

	public void setPresences(Set<Presence> presences) {
		this.presences = presences;
	}

}
