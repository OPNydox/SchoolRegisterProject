package com.example.school.utilities.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.example.school.database.entities.Teacher;
import com.example.school.database.entities.User;
import com.example.school.utilities.enums.UserRole;
import com.example.school.viewModels.Interfaces.UserViewModel;
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

    public static UserViewModel mapTeacherEntityToUserViewModel(Teacher teacherEntity) {
        UserViewModel userViewModelResult = new UserViewModel();

        userViewModelResult.setId(Long.toString(teacherEntity.getUserId()));
        userViewModelResult.setUserRole(UserRole.TEACHER);
        userViewModelResult.setEmail(teacherEntity.getEmail());
        userViewModelResult.setName(teacherEntity.getName());
        userViewModelResult.setCourses(CourseMapper.mapEntityToCourseViewModel(teacherEntity.getCourses()));

        return userViewModelResult;
    }

    public static Set<UserViewModel> mapTeacherEntityToUserViewModel(Iterable<Teacher> teacherEntities) {
        Set<UserViewModel> resultViewModels = new HashSet<>();

        for (Teacher teacherEntity : teacherEntities) {
            resultViewModels.add(mapTeacherEntityToUserViewModel(teacherEntity));
        }

        return resultViewModels;
    }

    public static Set<UserViewModel>mapTeacherEntityToSimpleUserViewModel(Iterable<Teacher> teacherEntities){
        Set<UserViewModel> result = new HashSet<>();

        for (Teacher teacher : teacherEntities) {
            result.add(mapTeacherEntityToSimpleUserViewModel(teacher));
        }

        return result;
    }

    public static UserViewModel mapTeacherEntityToSimpleUserViewModel(Teacher teacherEntity) {
        UserViewModel userViewModelResult = new UserViewModel();

        userViewModelResult.setId(Long.toString(teacherEntity.getUserId()));
        userViewModelResult.setUserRole(UserRole.TEACHER);
        userViewModelResult.setEmail(teacherEntity.getEmail());
        userViewModelResult.setName(teacherEntity.getName());

        return  userViewModelResult;
    }
}
