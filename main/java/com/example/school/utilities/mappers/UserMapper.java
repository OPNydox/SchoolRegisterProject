package com.example.school.utilities.mappers;

import com.example.school.database.entities.User;
import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;

import java.util.Arrays;
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
        resultModel.setPassword(user.getPassword());
        resultModel.setCourses(CourseMapper.mapEntityToCourseViewModel(user.getCourses()));
        resultModel.setGrades(GradeMapper.mapGradeToViewModel(user.getGrades()));
        switch (user.getUserType()) {
            case 0:
                resultModel.setUserRole(UserRole.ADMIN);
                break;
            case 1:
                resultModel.setUserRole(UserRole.STUDENT);
                break;
            case 2:
                resultModel.setUserRole(UserRole.TEACHER);
                break;
            default:
                resultModel.setUserRole(UserRole.UNAUTHORIZED);

        }

        return resultModel;
    }
}
