package com.wonderlabz.bankingaccountsystem.exception;

public class LowInitialDepositException extends RuntimeException{
    public LowInitialDepositException(String msg){
        super(msg);
    }
}
