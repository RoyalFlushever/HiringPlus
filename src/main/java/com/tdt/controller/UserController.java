package com.tdt.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tdt.dao.UserDAO;
import com.tdt.dto.BaseResponseDTO;
import com.tdt.dto.DTO;
import com.tdt.dto.LoggedInUserResponse;
import com.tdt.dto.UserDTO;
import com.tdt.listener.WebAppListener;
import com.tdt.model.DashboardData;
import com.tdt.service.TokenAuthenticationService;
import com.tdt.service.UserService;
import com.tdt.service.dto.UserAuthentication;

/**
 * User controller
 */
@RestController
public class UserController extends AbstractController {

    @Autowired
    Environment environment;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ServletContext servletContext;
    
    // @RequestMapping(value = "/reset", method = RequestMethod.PUT)
    // public BaseResponseDTO reset(@Valid @RequestBody ResetPasswordDTO
    // resetPasswordDTO, HttpServletResponse response) {
    //
    // User user = userService.findByEmail(resetPasswordDTO.getEmail());
    // if (user != null) {
    // user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
    // userService.save(user);
    // return BaseResponseDTO.builder().status(200).message("Password
    // changed").build();
    // }
    // else {
    // response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    // return BaseResponseDTO.builder().status(400).message("User not
    // existed").build();
    // }
    // }
    
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping("/")
    public BaseResponseDTO root() {
        return BaseResponseDTO.builder().status(200).message("Welcome to Hiring plus").build();
    }

    @RequestMapping("/hello")
    public BaseResponseDTO hello() {
        return BaseResponseDTO.builder().status(200).message("Hello").build();
    }

    @RequestMapping("/hellosecure")
    public BaseResponseDTO helloSecure() {
        return BaseResponseDTO.builder().status(200).message("Hello is Secured").build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public BaseResponseDTO logout(HttpServletRequest request) {

        UserAuthentication authentication = (UserAuthentication) tokenAuthenticationService.getAuthentication(request);
        Map<String, String> validMap = (Map<String, String>) servletContext.getAttribute(WebAppListener.VALID_TOKEN_MAP);
        Map<String, String> blacklistedMap = (Map<String, String>) servletContext.getAttribute(WebAppListener.TOKEN_BLACK_LIST_MAP);
        blacklistedMap.put(authentication.getDetails().getUsername(), validMap.get(authentication.getDetails().getUsername()));
        return BaseResponseDTO.builder().status(HttpServletResponse.SC_OK).message("logged out").build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup", produces = "application/json")
    public BaseResponseDTO signup(@Valid @RequestBody UserDTO user) {

        userService.saveUser(user);
        return BaseResponseDTO.builder().status(HttpStatus.OK.value()).message("user created").build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public DTO login(@RequestBody UserDTO login, HttpServletRequest request, HttpServletResponse response) throws ServletException {

        LOGGER.debug("Login request");
        Authentication authentication = tokenAuthenticationService.getAuthentication(login);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getDetails();
        
        UserDetails existingUser = userService.loadUserByUsername(user.getUsername());
        
        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return BaseResponseDTO.builder().status(HttpServletResponse.SC_UNAUTHORIZED).message("Wrong credentials").build();
        } else {

            Map<String, String> validMap = (Map<String, String>) servletContext.getAttribute(WebAppListener.VALID_TOKEN_MAP);
            tokenAuthenticationService.addAuthentication(response, (UserAuthentication) authentication);
            validMap.put(user.getUsername(), tokenAuthenticationService.getToken(response));
            tokenAuthenticationService.clearBlackList();
            com.tdt.model.User result = userService.findByEmail(((User) authentication.getDetails()).getUsername());
            LoggedInUserResponse resp = LoggedInUserResponse.builder().id(result.getId()).email(result.getEmail())
                                .firstName(result.getFirstName()).lastName(result.getLastName()).accountId(result.getAccount().getId()).build();
            return resp;
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/user/{userId}", produces="application/json")
    public com.tdt.model.User getUserById(@PathVariable Long userId) {
        return findUser(userId);
    }
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/dashboard",
            produces = "application/json")
    public DashboardData getDashboardData() {
        final com.tdt.model.User currentUser = userService
                .findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userService.getDashboardData(currentUser);
    }
}
