package com.example.school.services.implementations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.exceptions.ValueException;
import com.example.school.factories.GradeFactory;
import com.example.school.repositories.CourseRepository;
import com.example.school.repositories.GradeRepository;
import com.example.school.services.interfaces.IGradeService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.ReturnResult;
import com.example.school.utilities.ServiceReturnResult;
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

	@Autowired
	private GradeFactory gradeFactory;

	private ServiceReturnResult returnResult;

	@Override
	public ServiceReturnResult addGrade(GradeViewModel gradeModel) {
		Grade newGrade = new Grade();
		Student gradeStudent = new Student();
		ModelDecorator decorator = new ModelDecorator(gradeModel);

		this.returnResult = new ServiceReturnResult();

		this.returnResult.addErrorMsg(decorator.validateModel(new GradeVMValidator()));
		
		if (!this.returnResult.isSuccessful()) {
			return this.returnResult;
		}

		ServiceReturnResult factoryResult;
		
		factoryResult = gradeFactory.getEntity(gradeModel);

		if (!factoryResult.isSuccessful()) {
			this.returnResult.addErrorMsg(factoryResult.getErrorMessages());
			return this.returnResult;
		}

		newGrade = (Grade) factoryResult.getReturnResultObject();
		
		saveEntity(newGrade);

		return this.returnResult;
	}

	private void saveEntity(Grade grade) {
		this.gradeRepository.save(grade);
	}

}


/*try {
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
return newGrade;*/
