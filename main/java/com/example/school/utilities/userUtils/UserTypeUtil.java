package com.example.school.utilities.userUtils;

public class UserTypeUtil {
    private int userType;

    public UserTypeUtil(int userType) {
        this.userType = userType;
    }

    private UserType getUserType () {
        switch (this.userType) {
            case 1:
                return UserType.STUDENT;
            case 2:
                return UserType.TEACHER;
            default:
                return UserType.NONE;
        }
    }

    public boolean isStudent() {
        if (getUserType() == UserType.STUDENT) {
            return true;
        }

        return false;
    }


}
