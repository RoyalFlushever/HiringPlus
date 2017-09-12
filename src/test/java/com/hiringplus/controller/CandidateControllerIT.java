package com.hiringplus.controller;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.tdt.Application;
import com.tdt.dao.CandidateDao;
import com.tdt.model.Contact;
import com.tdt.model.EducationDetail;
import com.tdt.model.ExperienceDetail;
import com.tdt.model.Candidate;
import com.tdt.service.TokenAuthenticationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles(value = { "dev" })
public class CandidateControllerIT {

    private CandidateDao dao;
    private Candidate profile;
    public static final String BASE_URI = "http://localhost:8080/user/";

    @Before
    public void setup() {
        profile = new Candidate();
        profile.setName("FirstN LastN");
        profile.setPhone("7049061127");
        profile.setLocation("Boston, MA");
        profile.setEmail("test@test.com");
        profile.setWebsite("http://haha.com");
        ExperienceDetail experience1 = new ExperienceDetail();
        experience1.setCompanyName("Next Step Living");
        experience1.setTitle("Senior Software Engineer");
        experience1.setLocation("Boston, MA");
        experience1.setTimePeriod("Jul2015-Mar2016(9 mos)");
        ExperienceDetail experience2 = new ExperienceDetail();
        experience2.setCompanyName("Optaros");
        experience2.setTitle("Senior Engineer");
        experience2.setLocation("Boston, MA");
        experience2.setTimePeriod("May2014-June2015(1 yr 2 mos");
        Set<ExperienceDetail> experienceDetails = new HashSet<>();
        experienceDetails.add(experience1);
        experienceDetails.add(experience2);
        profile.setExperienceDetails(experienceDetails);
        
        EducationDetail education1 = new EducationDetail();
        education1.setSchool("UP Technical University");
    }

    @Test
    public void save() {
        String token = RestAssured.given().baseUri(BASE_URI)
                .body("{\n" + "  \"email\" : \"khoi@yahoo.com\",\n" + "  \"password\" : \"123456\"\n" + "\n" + "}").contentType(ContentType.JSON)
                .post("/login").then().statusCode(200).extract().header(TokenAuthenticationService.AUTH_HEADER_NAME);

        RestAssured.given().baseUri(BASE_URI).header(TokenAuthenticationService.AUTH_HEADER_NAME, token).pathParam("userId", "8").body(profile)
                .contentType(ContentType.JSON).post("/candidate").then().statusCode(200);
    }
}
