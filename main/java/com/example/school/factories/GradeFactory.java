package com.example.school.factories;

import java.util.ArrayList;
import java.util.List;

import com.example.school.database.entities.Course;
import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.services.interfaces.ICourseService;
import com.example.school.services.interfaces.IStudentService;
import com.example.school.utilities.NumberHandler;
import com.example.school.utilities.ServiceReturnResult;
import com.example.school.viewModels.GradeViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeFactory {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICourseService courseService;

    public ServiceReturnResult<Grade> getEntity(GradeViewModel gradeViewModel) {
        List<String> errorContainer = new ArrayList<>();
        ServiceReturnResult<Grade> gradeResult = new ServiceReturnResult<>();
        Grade newGrade = new Grade();
        Double mark;
        Student student;
        Course course;

        mark = getMark(gradeViewModel.getMark(), errorContainer);
        student = getStudent(gradeViewModel.getStudentId(), errorContainer);
        course = getCourse(gradeViewModel.getCourseId(), errorContainer);

        newGrade.setMark(mark);
        newGrade.setStudent(student);
        newGrade.setCourseGrade(course);

        student.getGrades().add(newGrade);
        course.getGrades().add(newGrade);

        gradeResult.addErrorMsg(errorContainer);
        gradeResult.setReturnResultObject(newGrade);

        return gradeResult;
    }

    private Double getMark(String markString, List<String> errorContainer) {
        ServiceReturnResult<Double> markResult;

        markResult = NumberHandler.parseStringToDouble(markString);

        if (markResult.hasErrors()) {
            return Double.NaN;
        }

        return markResult.getReturnResultObject();
    }

    private Student getStudent(Long studentID, List<String> errorContainer) {
        ServiceReturnResult<Student> studentResult = new ServiceReturnResult<>();

        studentResult = this.studentService.findStudentEntityById(studentID);

        if (studentResult.hasErrors()) {
            errorContainer.addAll(studentResult.getErrorMessages());
            return new Student();
        }

        return (Student) studentResult.getReturnResultObject();
    }

    private Course getCourse(Long courseId, List<String> errorContainer) {
        ServiceReturnResult<Course> courseResult = new ServiceReturnResult<>();

        courseResult = this.courseService.getCourseById(courseId);

        if (courseResult.hasErrors()) {
            errorContainer.addAll(courseResult.getErrorMessages());
        }

        return courseResult.getReturnResultObject();
    }
}