package com.tdt.service.impl;

import com.tdt.dao.CandidateDao;
import com.tdt.dao.NoteDao;
import com.tdt.dao.ReminderDao;
import com.tdt.dao.UserDAO;
import com.tdt.dto.UserDTO;
import com.tdt.model.DashboardData;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Service
@Qualifier("userService")
@Transactional
public class UserService implements com.tdt.service.UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CandidateDao candidateDao;

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private ReminderDao reminderDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.tdt.model.User user = userDAO.findByEmail(username);
        return user != null ? new User(user.getEmail(), user.getPassword(),
                Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority("USER")})) : null;
    }

    public com.tdt.model.User saveUser(UserDTO userDTO) {

        return userDAO.save(com.tdt.model.User
                .builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build());
    }

    public void delete(String email) {
        userDAO.deleteByEmail(email);
    }

    @Override
    public com.tdt.model.User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public void save(com.tdt.model.User user) {
        userDAO.save(user);
    }

    @Override
    public com.tdt.model.User findByResetToken(String token) {
        return userDAO.findByResetToken(token);
    }

    @Override
    public DashboardData getDashboardData(com.tdt.model.User user) {
        val last24Hours = Calendar.getInstance();
        last24Hours.add(Calendar.DAY_OF_MONTH, -1);

        val next7days = Calendar.getInstance();
        next7days.add(Calendar.DAY_OF_MONTH, 7);

        val candidates = candidateDao.countByUpdatedAtAfter(last24Hours.getTime());
        val notes = noteDao.countByCreatedAtAfter(last24Hours.getTime());
        val reminders = reminderDao.countByReminderDateBefore(next7days.getTime());

        return new DashboardData(candidates, notes, reminders);
    }
}
