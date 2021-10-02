package com.example.school.services.implementations;

import com.example.school.utilities.mappers.GradeMapper;
import com.example.school.viewModels.CourseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.database.entities.Grade;
import com.example.school.factories.GradeFactory;
import com.example.school.repositories.GradeRepository;
import com.example.school.services.interfaces.IGradeService;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.GradeViewModel;
import com.example.school.viewModels.decorators.GradeVMValidator;
import com.example.school.viewModels.decorators.ModelDecorator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GradeServiceImpl  implements IGradeService {
	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeFactory gradeFactory;

	@Override
	public ServiceReturnResult<Grade> addGrade(GradeViewModel gradeModel) {
		Grade newGrade = new Grade();
		ModelDecorator decorator = new ModelDecorator(gradeModel);
		ServiceReturnResult<Grade> result = new ServiceReturnResult<>();

		result.addErrorMsg(decorator.validateModel(new GradeVMValidator()));
		
		if (result.hasErrors()) {
			return result;
		}

		ServiceReturnResult<Grade> factoryResult;
		
		factoryResult = gradeFactory.getEntity(gradeModel);

		if (factoryResult.hasErrors()) {
			result.addErrorMsg(factoryResult.getErrorMessages());
			return result;
		}

		newGrade = factoryResult.getReturnResultObject();
		
		saveEntity(newGrade);

		return result;
	}

	private void saveEntity(Grade grade) {
		this.gradeRepository.save(grade);
	}

	@Override
	public Set<GradeViewModel> getAllGradesForPerson(String personEmail) {
		Set<GradeViewModel> gradeViewModelResult = new HashSet<>();
		Set<Grade> grades = (Set<Grade>) gradeRepository.findAll();

		for (Grade grade : grades) {
			if (grade.getStudent().getEmail().equals(personEmail)) {
				gradeViewModelResult.add(GradeMapper.mapGradeToViewModel(grade));
			}
		}

		return gradeViewModelResult;
	}

	@Override
	public Set<GradeViewModel> getGradesForCourse(CourseViewModel course) {
		Set<Grade> gradeEntities =new HashSet<>();// gradeRepository.findAllGradesForClass(/*course.getId()*/);
		Set<GradeViewModel> gradeViewModels = GradeMapper.mapGradeToViewModel(gradeEntities);

		course.setGrades(gradeViewModels);
		return gradeViewModels;
	}
}
