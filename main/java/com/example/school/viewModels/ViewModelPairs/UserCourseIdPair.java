package com.example.school.viewModels.ViewModelPairs;

import com.example.school.database.entities.User;
import com.example.school.viewModels.Interfaces.UserViewModel;

public class UserCourseIdPair {
    private UserViewModel user;

    private Long courseId;

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
