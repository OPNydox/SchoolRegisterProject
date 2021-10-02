package com.example.school.viewModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentProfileViewModel {
    private Set<CourseViewModel> courses;


    public StudentProfileViewModel() {
        courses = new HashSet<>();
    }

    public Set<CourseViewModel> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseViewModel> courses) {
        this.courses = courses;
    }

}
