package com.hiringplus.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.tdt.Application;
import com.tdt.dto.UserDTO;
import com.tdt.service.TokenAuthenticationService;
import com.tdt.service.UserService;


/**
 * Main integration test suit for UserController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@ActiveProfiles(value = {"qa"})
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    public static final String TEST_EMAIL = "khoi@yahoo.com";
    public static final String BASE_URI = "http://localhost:8080";
    @Autowired
    private UserService userService;

    @Before
    public void createData() {

        try {
            userService.saveUser(UserDTO.builder().email(TEST_EMAIL).password("123456").build());
        } catch (DataIntegrityViolationException e) {
        }
    }

    @Test
    public void testLoginSuccess() {

        String token = RestAssured.given().baseUri(BASE_URI).body("{\n" +
                "  \"email\" : \"khoi@yahoo.com\",\n" +
                "  \"password\" : \"123456\"\n" +
                "\n" +
                "}").contentType(ContentType.JSON).post("/login")
                .then().statusCode(200).extract().header(TokenAuthenticationService.AUTH_HEADER_NAME);

        RestAssured.given().baseUri(BASE_URI).header(TokenAuthenticationService.AUTH_HEADER_NAME, token).get("/hello")
                .then().statusCode(200);
    }

    /**
     * wrong password
     */
    @Test
    public void testLoginFail() {

        RestAssured.given().baseUri(BASE_URI).body("{\n" +
                "  \"email\" : \"khoi@yahoo.com\",\n" +
                "  \"password\" : \"123\"\n" +
                "\n" +
                "}").contentType(ContentType.JSON).post("/login")
                .then().statusCode(401);
    }

    @Test
    public void testSignupSuccess() {

        userService.delete("khoi@yahoo.com");
        RestAssured.given().baseUri(BASE_URI).body("{\n" +
                "  \"email\" : \"khoi@yahoo.com\",\n" +
                "  \"password\" : \"123456\",\n" +
                "  \"repassword\" : \"123456\"\n" +
                "\n" +
                "}").contentType(ContentType.JSON).post("/signup")
                .then().statusCode(200);
    }

    /**
     * password and confirm password don't match
     */
    @Test
    public void testSignupFail1() {

        userService.delete("khoi@yahoo.com");
        RestAssured.given().baseUri(BASE_URI).body("{\n" +
                "  \"email\" : \"khoi@yahoo.com\",\n" +
                "  \"password\" : \"123456\",\n" +
                "  \"repassword\" : \"123\"\n" +
                "\n" +
                "}").contentType(ContentType.JSON).post("/signup")
                .then().statusCode(400);
    }

    /**
     * email already registered
     */
    @Test
    public void testSignupFail2() {

        RestAssured.given().baseUri(BASE_URI).body("{\n" +
                "  \"email\" : \"khoi@yahoo.com\",\n" +
                "  \"password\" : \"123456\",\n" +
                "  \"repassword\" : \"123456\"\n" +
                "\n" +
                "}").contentType(ContentType.JSON).post("/signup")
                .then().statusCode(400);
    }

    @Test
    public void testLogoutSuccess() {

        //first login and get token
        String token = RestAssured.given().baseUri(BASE_URI).body("{\n" +
                "  \"email\" : \"khoi@yahoo.com\",\n" +
                "  \"password\" : \"123456\"\n" +
                "\n" +
                "}").contentType(ContentType.JSON).post("login")
                .then().statusCode(200).extract().header(TokenAuthenticationService.AUTH_HEADER_NAME);

        //next logout
        RestAssured.given().baseUri(BASE_URI).header(TokenAuthenticationService.AUTH_HEADER_NAME, token).get("/logout")
                .then().statusCode(200);
        //then access with old token this would be forbidden
        RestAssured.given().baseUri(BASE_URI).get("/hello")
                .then().statusCode(403);
    }

    @Test
    public void testLogoutFail() {

        RestAssured.given().baseUri(BASE_URI).get("/logout")
                .then().statusCode(403);
    }
//
//    @Test
//    public void testResetPassword() {
//
//        RestAssured.given().body("{\n" +
//                "  \"email\": \"khoi@yahoo.com\",\n" +
//                "  \"oldPassword\" : \"123456\",\n" +
//                "  \"newPassword\" : \"123123\", \n" +
//                "  \"reNewPassword\" : \"123123\"\n" +
//                "}")
//                .contentType("application/json")
//                .baseUri(BASE_URI)
//                .put("/reset").then()
//                .statusCode(200);
//    }
}
