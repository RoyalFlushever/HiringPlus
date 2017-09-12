package com.tdt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdt.model.Reminder;

import java.util.Date;

public interface ReminderDao extends JpaRepository<Reminder, Long>{

    int countByReminderDateBefore(Date date);

    Reminder findByCandidateId(Long id);
}
