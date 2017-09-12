package com.tdt.controller;

import com.tdt.dao.SkillDao;
import com.tdt.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiringplus.exception.AccountNotFoundException;
import com.hiringplus.exception.CandidateNotFoundException;
import com.hiringplus.exception.JobNotFoundException;
import com.hiringplus.exception.UserNotFoundException;
import com.tdt.dao.AccountDao;
import com.tdt.dao.CandidateDao;
import com.tdt.dao.JobDao;
import com.tdt.dao.UserDAO;
import com.tdt.model.Account;
import com.tdt.model.Candidate;
import com.tdt.model.Job;
import com.tdt.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbstractController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private CandidateDao candidateDao;

    public User findUser(Long userId) {
        return userDao.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public Account findAccount(Long accountId) {
        return accountDao.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    public Job findJob(Long jobId) {
        return jobDao.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId));
    }

    public Candidate findCandidate(Long candidateId) {
        return candidateDao.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException(candidateId));
    }

    protected static Optional<Skill> findSkillByName(List<Skill> skills, String name) {
        return skills.stream().filter(skill -> skill.getName().equals(name)).findFirst();
    }

    protected List<Skill> findExistingSkillsByNames(Collection<Skill> skills) {
        List<String> skillNames = skills.stream().map(Skill::getName).collect(Collectors.toList());
        return skillDao.findByNameIn(skillNames);
    }

}
