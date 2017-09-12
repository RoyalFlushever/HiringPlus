package com.tdt.listener;


import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

@Component
public class WebAppListener implements ServletContextListener {

    public static final String TOKEN_BLACK_LIST_MAP = "blacklist";
    public static final String VALID_TOKEN_MAP = "valid";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(TOKEN_BLACK_LIST_MAP, new HashMap<String, String>());
        sce.getServletContext().setAttribute(VALID_TOKEN_MAP, new HashMap<String, String>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
