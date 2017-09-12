package com.tdt.dao;

import com.tdt.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface NoteDao extends JpaRepository<Note, Long> {

    int countByCreatedAtAfter(Date date);
}
