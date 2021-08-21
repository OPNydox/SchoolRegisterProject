package com.example.school.utilities.mappers;

import com.example.school.database.entities.User;
import com.example.school.viewModels.Interfaces.UserViewModel;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {
    public static Set<UserViewModel> mapUserToViewModel(Set<User> users) {
        Set<UserViewModel> resultSet = new HashSet<>();

        for (User user : users) {
            resultSet.add(mapUserToViewModel(user));
        }

        return resultSet;
    }

    public static UserViewModel mapUserToViewModel(User user) {
        UserViewModel resultModel = new UserViewModel();

        resultModel.setName(user.getName());
        resultModel.setId(Long.toString(user.getUserId()));
        resultModel.setEmail(user.getEmail());
        resultModel.setCourses(CourseMapper.mapEntityToCourseViewModel(user.getCourses()));

        return resultModel;
    }
}
