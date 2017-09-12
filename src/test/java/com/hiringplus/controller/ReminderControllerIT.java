package com.hiringplus.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.tdt.Application;
import com.tdt.dao.CandidateDao;
import com.tdt.dao.ReminderDao;
import com.tdt.dao.UserDAO;
import com.tdt.model.Candidate;
import com.tdt.model.Reminder;
import com.tdt.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ReminderControllerIT {
//
//    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
//            Charset.forName("utf8"));
//
//    private MockMvc mockMvc;
//    private HttpMessageConverter mappingJackson2HttpMessageConverter;
//
//    @Autowired
//    private UserDAO userDao;
//
//    @Autowired
//    private CandidateDao candidateDao;
//
//    @Autowired
//    private ReminderDao reminderDao;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    void setConverters(HttpMessageConverter<?>[] converters) {
//        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
//                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
//                .findAny()
//                .orElse(null);
//        assertNotNull("the JSON message converter must not be null");
//    }
//    
//    User user;
//    Candidate candidate;
//    Reminder reminder;
//
//    @Before
//    public void setup() throws Exception {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//        user = new User().builder()
//                .firstName("Testing FName")
//                .lastName("Testing LName")
//                .email("test@test.com")
//                .password("password")
//                .build();
//        user = userDao.save(user);
//        
//        candidate = new Candidate().builder()
//                .name("TestReminder")
//                .userId(user.getId())
//                .createdAt(new Date())
//                .updatedAt(new Date())
//                .build();
//        candidate = candidateDao.save(candidate);
//        
//    }
//    
//    @Test
//    public void saveReminder() throws Exception {
//        String reminderJson = json(new Reminder().builder()
//                .reminderDate(new Date())
//                .title("test reminder")
//                .build());
//        this.mockMvc.perform(post("/user/"+user.getId()+"/candidate/"+candidate.getId()+"/reminder")
//                .contentType(contentType)
//                .content(reminderJson))
//                .andExpect(status().isCreated());
//        
//    }
//    
//    protected String json(Object o) throws IOException {
//        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
//        this.mappingJackson2HttpMessageConverter.write(
//                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
//        return mockHttpOutputMessage.getBodyAsString();
//    }

}
