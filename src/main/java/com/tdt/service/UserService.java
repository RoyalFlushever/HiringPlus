package com.tdt.service;

import com.tdt.model.DashboardData;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.tdt.dto.UserDTO;
import com.tdt.model.User;

public interface UserService extends UserDetailsService {

    User saveUser(UserDTO userDTO);

    void delete(String email);

    User findByEmail(String email);

    void save(User user);

    User findByResetToken(String token);

    DashboardData getDashboardData(User user);
}
