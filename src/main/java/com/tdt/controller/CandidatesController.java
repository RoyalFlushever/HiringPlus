package com.tdt.controller;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tdt.dao.CandidateDao;
import com.tdt.dto.CandidateDTO;
import com.tdt.model.Candidate;
import com.tdt.model.Skill;
import com.tdt.model.User;

@RestController
public class CandidatesController extends AbstractController {

    @Autowired
    private CandidateDao candidateDao;

    private static final Logger LOGGER = Logger.getLogger(CandidatesController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/user/{userId}/candidate", produces = "application/json")
    public Candidate save(@PathVariable Long userId, @RequestBody Candidate profile) {

        LOGGER.debug("User id " + userId + " is saving a candidate profile");
        
        User user = findUser(userId);

        profile.setUserId(userId);
        profile.setAccountId(user.getAccount().getId());

        if (profile.getEducationDetails() != null) {
            profile.getEducationDetails().forEach(ed -> ed.setCandidate(profile));
        }
        if (profile.getExperienceDetails() != null) {
            profile.getExperienceDetails().forEach(ex -> ex.setCandidate(profile));
        }

        if(profile.getSkills() != null){
            List<Skill> existingSkills = findExistingSkillsByNames(profile.getSkills());
            profile.setSkills(
                    profile
                    .getSkills()
                    .stream()
                    .map(skill -> findSkillByName(existingSkills, skill.getName()).orElse(skill))
                    .collect(Collectors.toSet())
            );

            if (profile.getSkills() != null) {
                profile.getSkills().forEach(s -> {
                    if (s.getCandidates() == null) {
                        s.setCandidates(new HashSet<>());
                    }
                    s.getCandidates().add(profile);
                });
            }
        }

        

        profile.setCreatedAt(new Date(new java.util.Date().getTime()));
        profile.setUpdatedAt(new Date(new java.util.Date().getTime()));
        Candidate candidate = candidateDao.save(profile);
        LOGGER.info("Candidate created with id "+ candidate.getId());
        return candidate;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/candidates", produces = "application/json")
    public Set<Candidate> findByUserId(@PathVariable Long userId) {
        findUser(userId);
        return candidateDao.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/candidatesPaged", produces = "application/json")
    public Page<Candidate> findByUserIdPaged(@PathVariable Long userId, Pageable pageable) {
        findUser(userId);
        return candidateDao.findByUserIdOrderByUpdatedAtDesc(userId, pageable);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/account/{accountId}/candidates", produces = "application/json")
    public Set<Candidate> findByAccountId(@PathVariable Long accountId) {
        return candidateDao.findByAccountIdOrderByUpdatedAtDesc(accountId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/candidate/{id}", produces = "application/json")
    public Candidate findById(@PathVariable Long id) {
        return candidateDao.findByIdAndFetchNotesEagerly(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/candidate/{id}", produces = "application/json")
    public Candidate update(@PathVariable Long id, @RequestBody CandidateDTO profile) {
        return candidateDao.findById(id).map(candidate -> {
            if (profile.getEmail() != null && !profile.getEmail().isEmpty()) {
                candidate.setEmail(profile.getEmail());
            }
            if (profile.getPhone() != null && !profile.getPhone().isEmpty()) {
                candidate.setPhone(profile.getPhone());
            }
            if (profile.getResumeUrl() != null) {
                candidate.setResumeUrl(profile.getResumeUrl());
            }
            return candidateDao.save(candidate);
        }).orElse(null);
    }
}
