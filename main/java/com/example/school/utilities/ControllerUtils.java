package com.example.school.utilities;

import com.example.school.authentication.MyUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class ControllerUtils {
    public static MyUserPrincipal getUserPrincipal() {
        return (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
