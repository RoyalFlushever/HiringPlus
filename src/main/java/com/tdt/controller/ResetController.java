package com.tdt.controller;

import com.tdt.dto.BaseResponseDTO;
import com.tdt.dto.ResetPasswordDTO;
import com.tdt.model.User;
import com.tdt.service.TokenAuthenticationService;
import com.tdt.service.UserService;
import com.tdt.service.dto.UserAuthentication;
import com.tdt.utils.MailUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class ResetController {

    @Autowired
    Environment environment;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @RequestMapping(value = "/forgot")
    public
    @ResponseBody
    BaseResponseDTO forgot(@RequestParam String email, HttpServletResponse response) {

        User user = userService.findByEmail(email);
        if (user.getEmail().equals(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            MailUtil.send(email, "Reset your password", "Please go to this link to reset: " + environment.getProperty("reset_url") + token);
            user.setResetToken(token);
            userService.save(user);
            return BaseResponseDTO.builder().status(200).message("email sent").build();
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return BaseResponseDTO.builder().status(400).message("wrong email").build();
    }

    @RequestMapping(value = "/reset_pw", method = RequestMethod.POST)
    public
    @ResponseBody
    BaseResponseDTO resetPwd(@Valid ResetPasswordDTO resetPasswordDTO, HttpServletResponse response) {

        User user = userService.findByResetToken(resetPasswordDTO.getToken());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
            tokenAuthenticationService.addAuthentication(response,
                    new UserAuthentication(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority("USER")}))));

            user.setResetToken(null);
            userService.save(user);

            return BaseResponseDTO.builder().status(200).message(tokenAuthenticationService.getToken(response)).build();
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return BaseResponseDTO.builder().status(400).message("error").build();
    }

    @RequestMapping(value = "/reset_pw", method = RequestMethod.GET)
    public String resetForm(Model model, @RequestParam(required = false) String token) {

        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setToken(token);
        model.addAttribute("resetPasswordDTO", resetPasswordDTO);
        return "reset_form";
    }
}
