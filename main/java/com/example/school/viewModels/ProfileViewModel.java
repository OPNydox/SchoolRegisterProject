package com.example.school.viewModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfileViewModel {
    private Set<CourseViewModel> courses;

    private List<GradeViewModel> grades;

    public ProfileViewModel() {
        courses = new HashSet<>();
        grades  = new ArrayList<>();
    }

    public Set<CourseViewModel> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseViewModel> courses) {
        this.courses = courses;
    }

    public List<GradeViewModel> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeViewModel> grades) {
        this.grades = grades;
    }
}
