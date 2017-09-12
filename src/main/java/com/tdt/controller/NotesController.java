package com.tdt.controller;

import java.sql.Date;

import com.tdt.dao.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tdt.dao.CandidateDao;
import com.tdt.dao.UserDAO;
import com.tdt.model.Candidate;
import com.tdt.model.Note;
import com.tdt.model.User;

@RestController
public class NotesController {

    @Autowired
    private NoteDao dao;
    
    @Autowired
    private CandidateDao candidateDao;
    
    @Autowired
    private UserDAO userDao;
    
    @RequestMapping(method = RequestMethod.POST,
            value = "/candidate/{candidateId}/note",
            produces = "application/json")
    public Note addNote(@PathVariable Long candidateId, @RequestBody String content) {
        Candidate candidate = candidateDao.findOne(candidateId);
        final User author = userDao.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        candidate.setUpdatedAt(new Date(new java.util.Date().getTime()));
        candidate = candidateDao.save(candidate);
        final Note note = new Note();
        note.setCandidate(candidate);
        note.setCreatedBy(author);
        note.setCreatedAt(new Date(new java.util.Date().getTime()));
        note.setContent(content);
        dao.save(note);
        return note;
    }
}
