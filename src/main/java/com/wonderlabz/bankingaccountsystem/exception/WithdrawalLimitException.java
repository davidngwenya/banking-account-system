package com.wonderlabz.bankingaccountsystem.exception;

public class WithdrawalLimitException extends RuntimeException{
    public WithdrawalLimitException(String msg){
        super(msg);
    }
}
