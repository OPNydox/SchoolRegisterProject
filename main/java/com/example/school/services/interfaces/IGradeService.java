package com.example.school.services.interfaces;

import com.example.school.database.entities.Grade;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.GradeViewModel;

public interface IGradeService {
	
	ServiceReturnResult addGrade(GradeViewModel gradeModel);
}
