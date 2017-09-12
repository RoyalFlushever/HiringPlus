package com.tdt.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.tdt.listener.WebAppListener;
import com.tdt.service.TokenAuthenticationService;
import com.tdt.service.dto.UserAuthentication;

public class JJWTFilter extends GenericFilterBean {

    private final TokenAuthenticationService authenticationService;

    public JJWTFilter(TokenAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            final HttpServletResponse response = (HttpServletResponse) res;
            final HttpServletRequest request = (HttpServletRequest) req;

            addCorsHeaders(response);

            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                checkBlackList(request);

                UserAuthentication authentication = authenticationService.getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                SecurityContextHolder.getContext().setAuthentication(null);
            }

        } catch (RuntimeException e) {
            ((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void addCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-AUTH-TOKEN, Content-Type, Accept");
        response.setHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN, Content-Type");
    }

    private void checkBlackList(HttpServletRequest request) {
        Map<String, String> blackListedTokenMap =
                (Map<String, String>) request
                        .getSession()
                        .getServletContext()
                        .getAttribute(WebAppListener.TOKEN_BLACK_LIST_MAP);
        String authToken = authenticationService.getToken(request);
        if (authToken != null && blackListedTokenMap.containsValue(authToken)) {
            throw new RuntimeException("token invalidated");
        }
    }

}