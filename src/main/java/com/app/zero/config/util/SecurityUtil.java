package com.app.zero.config.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class SecurityUtil {

    public static String getLoginUsername(){
        Authentication loginUser = SecurityContextHolder.getContext().getAuthentication();
        String user = loginUser.getName();
        log.trace("trace log={}", user);
        System.out.println(user);
        return user;
    }
}
