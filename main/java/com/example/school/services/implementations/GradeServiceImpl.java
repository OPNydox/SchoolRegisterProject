package com.example.school.services.implementations;

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

}
