package com.example.school.utilities.mappers;

import java.util.ArrayList;

import com.example.school.database.entities.Student;
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
}
