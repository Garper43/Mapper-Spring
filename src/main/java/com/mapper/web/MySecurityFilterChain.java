package com.mapper.web;

import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MySecurityFilterChain implements SecurityFilterChain {

    @Override
    public boolean matches(jakarta.servlet.http.HttpServletRequest request) {
        return false;
    }

    @Override
    public List<jakarta.servlet.Filter> getFilters() {
        return null;
    }
}
