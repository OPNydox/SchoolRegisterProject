package com.example.school.utilities.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.school.database.entities.Grade;
import com.example.school.database.entities.Student;
import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;
import com.example.school.viewModels.StudentViewModel;

public class StudentMapper {
    public static Iterable<StudentViewModel> mapStudentsToViewModels(Iterable<Student> students) {
        ArrayList<StudentViewModel> result = new ArrayList<>();

        for (Student student : students) {
            result.add(mapUserToViewModel(student));
        }

        return result;
    }

    public static StudentViewModel mapUserToViewModel(Student student) {
        StudentViewModel result = new StudentViewModel();
        
        result = generateViewModel(student);

        return result;
    }

    private static StudentViewModel generateViewModel(Student student) {
        StudentViewModel result = new StudentViewModel();

        result.setEmail(student.getEmail());
        //result.setId(Long.toString(student.getStudentId()));
        result.setName(student.getName());
        
        return result;
    }

    public static Set<UserViewModel> mapStudentEntityToUserViewModel(Iterable<Student> students) {
        Set<UserViewModel> result = new HashSet<>();

        for (Student student : students) {
            result.add(mapStudentEntityToUserViewModel(student));
        }

        return result;
    }

    public static UserViewModel mapStudentEntityToUserViewModel(Student student) {
        UserViewModel resultUserViewModel = new UserViewModel();

        resultUserViewModel.setId(Long.toString(student.getUserId()));
        resultUserViewModel.setUserRole(UserRole.STUDENT);
        resultUserViewModel.setName(student.getName());
        resultUserViewModel.setEmail(student.getEmail());
        resultUserViewModel.setGrades(GradeMapper.mapGradeToViewModel(student.getGrades()));
        resultUserViewModel.setCourses(CourseMapper.mapEntityToCourseViewModel(student.getCourses()));

        return resultUserViewModel;
    }

    public static UserViewModel mapStudentEntityToSimpleUserViewModel(Student student) {
        UserViewModel resultUserViewModel = new UserViewModel();

        resultUserViewModel.setId(Long.toString(student.getUserId()));
        resultUserViewModel.setUserRole(UserRole.STUDENT);
        resultUserViewModel.setName(student.getName());
        resultUserViewModel.setEmail(student.getEmail());

        return resultUserViewModel;
    }
    public static Set<UserViewModel> mapStudentEntityToSimpleUserViewModel(Set<Student> student) {
        Set<UserViewModel> result = new HashSet<>();

        for (Student userViewModel : student) {
            result.add(mapStudentEntityToSimpleUserViewModel(userViewModel));
        }
        return result;
    }
}
