package com.tdt.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public final class TokenHandler {

    @Autowired
    Environment environment;
    @Autowired
    private UserService userService;

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(environment.getProperty("jwt.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println("User name is "+ username);
        return (User) userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, environment.getProperty("jwt.secret"))
                .setExpiration(new DateTime().plusDays(1).toDate()) // token will be expired after 1 day
                .compact();
    }
}