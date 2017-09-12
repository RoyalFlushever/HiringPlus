package com.tdt.service;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.tdt.dao.UserDAO;
import com.tdt.dto.UserDTO;
import com.tdt.listener.WebAppListener;
import com.tdt.service.dto.UserAuthentication;


public class TokenAuthenticationService {

    public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ServletContext servletContext;

    public TokenAuthenticationService() {
    }

    public TokenAuthenticationService(TokenHandler tokenHandler, UserDAO userDAO) {
        this.tokenHandler = tokenHandler;
        this.userDAO = userDAO;
    }

    public String getToken(HttpServletRequest request) {

        return request.getHeader(AUTH_HEADER_NAME);
    }

    public void clearBlackList() {
        servletContext.setAttribute(WebAppListener.TOKEN_BLACK_LIST_MAP, new HashMap<String, String>());
    }

    public UserAuthentication getAuthentication(UserDTO userDTO) {

        UserAuthentication userAuthentication =
                new UserAuthentication(new User(userDTO.getEmail(),
                        userDTO.getPassword(),
                        Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority("USER")})));
        return userAuthentication;
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        String token = tokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);
    }

    public UserAuthentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        System.out.println(">>>>>>>>>>>>>>>>>Found JWT Token "+ token);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    public String getToken(HttpServletResponse response) {
        return response.getHeader(AUTH_HEADER_NAME);
    }
}