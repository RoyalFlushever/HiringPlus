package com.hiringplus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6268556869172962691L;

    public UserNotFoundException(Long userId) {
        super("Could not found user with id '" + userId +"'.");
    }

}
