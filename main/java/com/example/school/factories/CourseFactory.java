package com.example.school.factories;

import com.example.school.database.entities.Course;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.CourseViewModel;

import org.springframework.stereotype.Component;

@Component
public class CourseFactory {
    public ServiceReturnResult<Course> getEntity(CourseViewModel courseViewModel) {
        Course course = new Course();
        ServiceReturnResult<Course> entityResult = new ServiceReturnResult<>();
        Integer honorarium;

        honorarium = getHonorarium(courseViewModel.getHonorarium());

        course.setCourseName(courseViewModel.getName());
        course.setSubject(courseViewModel.getSubject());
        course.setHonorarium(honorarium);

        entityResult.setReturnResultObject(course);

        return entityResult;

    }

    private Integer getHonorarium(String honorariumString) {
        ServiceReturnResult<Integer> numbResult;
        Integer honorarium;

        numbResult = NumberHandler.parseStringToInteger(honorariumString);

        if (numbResult.hasErrors()) {
            return 0;
        }

        honorarium = numbResult.getReturnResultObject();

        return honorarium;
    }
}
