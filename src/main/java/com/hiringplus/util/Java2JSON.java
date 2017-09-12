package com.hiringplus.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdt.model.Candidate;
import com.tdt.model.EducationDetail;
import com.tdt.model.ExperienceDetail;

public class Java2JSON {

//    public static void main(String[] args) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Candidate profile = new Candidate();
//        profile = prepareWithDummyData(profile);
//        //Object to JSON in file
//        //mapper.writeValue(new File("profile.json"), profile);
//
//        //Object to JSON in String
//        String jsonInString = mapper.writeValueAsString(profile);
//        System.out.println(jsonInString);
//    }

    private static Candidate prepareWithDummyData(Candidate profile) {
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
        
        return profile;
    }
}
