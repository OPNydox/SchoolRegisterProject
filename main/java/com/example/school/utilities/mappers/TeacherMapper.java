package com.example.school.utilities.mappers;

import java.util.ArrayList;

import com.example.school.database.entities.Teacher;
import com.example.school.viewModels.TeacherViewModel;

import antlr.collections.List;

public class TeacherMapper {

    public static Iterable<TeacherViewModel> mapTeacherEntityToViewModel(Iterable<Teacher> teachers) {
        ArrayList<TeacherViewModel> resultList = new ArrayList<>();
        
        for (Teacher teacher : teachers) {
            resultList.add(mapTeacherEntityToViewModel(teacher));
        }
        
        return resultList;
    }

    public static TeacherViewModel mapTeacherEntityToViewModel(Teacher teacher) {
        TeacherViewModel teacherViewModelResult = new TeacherViewModel();

        teacherViewModelResult.setEmail(teacher.getEmail());
        teacherViewModelResult.setName(teacher.getName());
        teacherViewModelResult.setSalary(Double.toString(teacher.getSalary()));

        return teacherViewModelResult;
    }
}
