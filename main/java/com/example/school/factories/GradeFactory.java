package com.example.school.factories;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.factories.interfaces.EntityFactory;
import com.example.school.services.implementations.StudentServiceImpl;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.GradeViewModel;
import com.example.school.viewModels.ViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GradeFactory implements EntityFactory {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICourseService courseService;

    private GradeViewModel gradeModel;

    private ServiceReturnResult result;

    public ServiceReturnResult getEntity(ViewModel viewModel) {
        Grade newGrade = new Grade();
        Double mark;
        Student student;
        Course course;

        initialize(viewModel);

        mark = getMark();
        student = getStudent();
        course = getCourse();

        newGrade.setMark(mark);
        newGrade.setStudent(student);
        newGrade.setClassGrade(course);

        student.getGrades().add(newGrade);
        course.getGrades().add(newGrade);

        this.result.setReturnResultObject(newGrade);

        return this.result;
    }

    private void initialize(ViewModel viewModel) {
        this.result = new ServiceReturnResult();
        this.gradeModel = (GradeViewModel) viewModel;
    }

    private Double getMark() {
        ServiceReturnResult markResult;

        markResult = NumberHandler.parseStringToDouble(gradeModel.getMark());

        if (!markResult.isSuccessful()) {
            this.result.addErrorMsg(markResult.getErrorMessages());
        }

        return (Double) markResult.getReturnResultObject();
    }

    private Student getStudent() {
        ServiceReturnResult studentResult = new ServiceReturnResult();

        studentResult = this.studentService.findStudentEntityById(this.gradeModel.getStudentId());

        if (!studentResult.isSuccessful()) {
            this.result.addErrorMsg(studentResult.getErrorMessages());
        }

        return (Student) studentResult.getReturnResultObject();
    }

    private Course getCourse() {
        ServiceReturnResult courseResult;

        courseResult = this.courseService.getCourseById(this.gradeModel.getCourseId());

        if (!courseResult.isSuccessful()) {
            this.result.addErrorMsg(courseResult.getErrorMessages());
        }

        return (Course) courseResult.getReturnResultObject();
    }
}