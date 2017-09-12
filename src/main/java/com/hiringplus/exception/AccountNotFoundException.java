package com.hiringplus.exception;

public class AccountNotFoundException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1719437726032039712L;

    public AccountNotFoundException(Long accountId) {
        super("Could not found account with id '" + accountId +"'.");
    }

}
