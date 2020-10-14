package com.example.school.servicesImplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Course;
import com.example.school.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repo;
	
	public void test() {
		if (repo == null) {
			System.out.println(repo);
			System.out.println("it is null");
			return;
		} else {
			System.out.println("hurah");
		}
		repo.save(new Course("Icoznanie", "10A", 12));
	}
}
