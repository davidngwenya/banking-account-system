package com.wonderlabz.bankingaccountsystem.exception;

public class OverDraftLimitException extends RuntimeException {
    public OverDraftLimitException(String msg) {
        super(msg);
    }
}
