package com.example.school.utilities;

public class PasswordPair {
    private String password;

    private String repeatPassword;

    public PasswordPair () {}

    public PasswordPair(String password, String repeatPassword) {
        this.setPassword(password);
        this.setRepeatPassword(repeatPassword);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
