package com.example.school.services.interfaces;

import com.example.school.database.entities.Grade;
import com.example.school.viewModels.GradeViewModel;

public interface IGradeService {
	
	Grade addGrade(GradeViewModel gradeModel);
}
