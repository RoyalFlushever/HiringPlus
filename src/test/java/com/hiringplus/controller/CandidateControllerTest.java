package com.hiringplus.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.sql.Date;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdt.Application;
import com.tdt.dao.AccountDao;
import com.tdt.dao.CandidateDao;
import com.tdt.dao.UserDAO;
import com.tdt.model.Account;
import com.tdt.model.Candidate;
import com.tdt.model.User;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=Application.class)
//@Rollback
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
//@FlywayTest
public class CandidateControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private CandidateDao candidateDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Account account;
    private User user;
    private Candidate candidate;
    
    @Autowired
    ObjectMapper mapper;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        //this.candidateDao.deleteAllInBatch();
        this.account = new Account();
        this.account.setName("IntegrationTests");
        this.account = accountDao.save(this.account);
        //this.account = accountDao.save(Account.builder().id(101L).name("IntegrationTests").build());
        this.user = userDao.save(User.builder().id(100L).email("integrationTest@test.com").firstName("Integratiom").lastName("Test").account(account).build());
        this.candidate = candidateDao.save(Candidate.builder().accountId(this.account.getId()).userId(this.user.getId())
                .name("TestMe").createdAt(new Date(new java.util.Date().getTime()))
                .updatedAt(new Date(new java.util.Date().getTime())).build());
    }
    
    public void getCandidateById() throws Exception {
        mockMvc.perform(get("/candidate/"+this.candidate.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(jsonPath("$.id", is(this.candidate.getId().intValue())))
        .andExpect(jsonPath("$.userId", is(this.candidate.getUserId().intValue())))
        .andExpect(jsonPath("$.accountId", is(this.candidate.getAccountId().intValue())))
        .andExpect(jsonPath("$.name", is(this.candidate.getName())));
    }
    
//    @FlywayTest(invokeCleanDB = false)
//    @Test
    public void createCandidate() throws Exception {
        String candidateJson = mapper.writeValueAsString(Candidate.builder().name("test2").build());
        this.mockMvc.perform(post("/user/"+this.user.getId()+"/candidate")
                .contentType(contentType)
                .content(candidateJson))
                .andExpect(status().isOk());
    }
    
    @After
    public void cleanup() throws Exception {
        candidateDao.delete(this.candidate.getId());
        userDao.delete(this.user.getId());
        accountDao.delete(this.account.getId());
    }
    
}
