package com.example.school.factories;

import java.security.Provider.Service;

import com.example.school.database.entities.Course;
import com.example.school.factories.interfaces.ModelFactory;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.servicesImplementations.CourseService;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;
import com.example.school.viewModels.Interfaces.ViewModel;

import org.dom4j.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest.H2ConsoleRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class CourseFactory implements ModelFactory {

    private ServiceReturnResult returnResult;

    private CourseViewModel courseViewModel;

    public ServiceReturnResult getEntity(ViewModel viewModel) {
        Course course = new Course();
        Integer honorarium;

        initialize(viewModel);

        honorarium = getHonorarium();

        course.setCourseName(courseViewModel.getName());
        course.setSubject(courseViewModel.getSubject());
        course.setHonorarium(honorarium);

        returnResult.setReturnResultObject(course);

        return this.returnResult;

    }

    private void initialize(ViewModel viewModel) {
        this.courseViewModel = (CourseViewModel) viewModel;
        this.returnResult = new ServiceReturnResult();
    }

    private Integer getHonorarium() {
        ServiceReturnResult numbResult;
        Integer honorarium;

        numbResult = NumberHandler.parseStringToInteger(courseViewModel.getHonorarium());

        if (!numbResult.isSuccessful()) {
            this.returnResult.addErrorMsg(numbResult.getErrorMessages());
            return 0;
        }

        honorarium = (Integer) numbResult.getReturnResultObject();

        return honorarium;
    }
}
