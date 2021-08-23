package com.example.school.utilities.enums;

public enum UserRole {
    UNAUTHORIZED(0),
    STUDENT(1),
    TEACHER(2),
    ADMIN(3);

    private int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
