package com.wonderlabz.bankingaccountsystem.exception;

public class UserAccountExistsException extends RuntimeException {
    public UserAccountExistsException(String msg) {
        super(msg);
    }
}
