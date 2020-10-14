package com.example.school.services.implementations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.exceptions.ValueException;
import com.example.school.repositories.CourseRepository;
import com.example.school.repositories.GradeRepository;
import com.example.school.services.interfaces.IGradeService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.Verificator;
import com.example.school.utilities.interfaces.IWriter;
import com.example.school.viewModels.GradeViewModel;
import com.example.school.viewModels.decorators.GradeVMValidator;
import com.example.school.viewModels.decorators.ModelDecorator;

@Service
public class GradeServiceImpl  implements IGradeService {
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private IWriter writer;

	@Override
	public Grade addGrade(GradeViewModel gradeModel) {
		Grade newGrade = new Grade();
		Student gradeStudent= new Student();
		ModelDecorator decorator = new ModelDecorator(gradeModel);
		List<String> validationResults = new ArrayList<>();
		
		validationResults.addAll(decorator.validateModel(new GradeVMValidator()));
		
		if (!validationResults.isEmpty()) {
			newGrade.setEmpty();
			writer.writeErrors(validationResults);
			return newGrade;
		}
		
		try {
			newGrade.setMark(Double.parseDouble(gradeModel.getMark()));
			newGrade.setClassGrade(courseRepository.findByName(gradeModel.getClassName()));
			gradeStudent = studentService.findStudentByEmail(gradeModel.getStudentEmail());
			Verificator.isEmpty(gradeStudent, "Can't find that student in database");
			newGrade.setStudent(gradeStudent);
			gradeStudent.getGrades().add(newGrade);
			gradeRepository.save(newGrade);
		} catch ( ValueException e) {
			writer.writeError(e.getMessage());
			newGrade.setEmpty();
		}
		return newGrade;
	}

}
