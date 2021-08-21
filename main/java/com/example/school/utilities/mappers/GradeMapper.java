package com.example.school.utilities.mappers;

import com.example.school.database.entities.Grade;
import com.example.school.viewModels.GradeViewModel;

import java.util.HashSet;
import java.util.Set;

public class GradeMapper {
    public static Set<GradeViewModel> mapGradeToViewModel(Set<Grade> grades) {
        Set<GradeViewModel> gradeViewModelSet = new HashSet<>();

        for (Grade grade : grades) {
            gradeViewModelSet.add(mapGradeToViewModel(grade));
        }

        return gradeViewModelSet;
    }

    public static GradeViewModel mapGradeToViewModel(Grade grade) {
        GradeViewModel gradeViewModel = new GradeViewModel();

        gradeViewModel.setMark(gradeViewModel.getMark());
        gradeViewModel.setClassName(grade.getClass().getName());
        gradeViewModel.setCourseId(grade.getCourseGrade().getCourseId());
        gradeViewModel.setStudentEmail(grade.getStudent().getEmail());

        return gradeViewModel;
    }
}
