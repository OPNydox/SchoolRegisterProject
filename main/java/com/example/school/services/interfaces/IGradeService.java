package com.example.school.services.interfaces;

import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.GradeViewModel;

import java.util.Set;

public interface IGradeService {
	
	ServiceReturnResult addGrade(GradeViewModel gradeModel);

	Set<GradeViewModel> getAllGradesForPerson(String personEmail);

	Set<GradeViewModel> getGradesForCourse(CourseViewModel course);
}
