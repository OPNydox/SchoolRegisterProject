package com.example.school.services.interfaces;

import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Presence;
import com.example.school.database.entities.Student;
import com.example.school.viewModels.PresenceViewModel;

public interface IPresenceService {
	
	Presence addPresence(PresenceViewModel presenceModel);
	
	List<Presence> getPresencesForClassName(String className);
	
	List<Presence> getPresencesForCourse(Course course);
	
	List<Presence> getPresenceForStudentEmail(String email);
	
	List<Presence> getPresenceForStudent(Student student);

	void addPresence(Long studentId, Long courseId);
}
