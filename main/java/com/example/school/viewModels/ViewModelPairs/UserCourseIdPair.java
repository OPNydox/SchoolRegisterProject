package com.example.school.viewModels.ViewModelPairs;

import com.example.school.database.entities.User;

public class UserCourseIdPair {
    private User user;

    private String courseId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
