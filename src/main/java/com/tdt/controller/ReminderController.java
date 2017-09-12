package com.tdt.controller;

import java.util.Date;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hiringplus.exception.UserNotFoundException;
import com.tdt.dao.CandidateDao;
import com.tdt.dao.ReminderDao;
import com.tdt.dao.UserDAO;
import com.tdt.model.Candidate;
import com.tdt.model.Reminder;
import com.tdt.model.User;

@RestController
public class ReminderController {

    @Autowired
    private CandidateDao candidateDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ReminderDao reminderDao;

    @RequestMapping(method = RequestMethod.POST,
                    value = "/user/{userId}/candidate/{candidateId}/reminder",
                    produces = "application/json")
    public Reminder saveReminder(@PathVariable Long userId, @PathVariable Long candidateId, @RequestBody Reminder reminder) {
        val user = this.validateUser(userId);
        val candidate = this.validateCandidate(candidateId);

        val existingReminder = reminderDao.findByCandidateId(candidate.getId());

        if (existingReminder != null) {
            reminder.setId(existingReminder.getId());
        }

        if (reminder != null) {
            reminder.setReminderDate(getReminderDate(reminder));
            reminder.setCandidate(candidate);
            reminder.setUser(user);
        }
        return reminderDao.save(reminder);
    }

    private Candidate validateCandidate(Long candidateId) {
        return candidateDao.findOne(candidateId);
    }

    private User validateUser(Long userId) {
        User user = userDao.findOne(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
    }

    private Date getReminderDate(Reminder reminder) {
        return reminder.getReminderDate();
    }
}
