package com.wonderlabz.bankingaccountsystem.model;

import javax.persistence.*;

public class CurrentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column(name = "account_balance")
    private Double accountBalance;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(name = "final_balance")
    private Double finalBalance;

    public CurrentAccount(){}

    public CurrentAccount(Double accountBalance, Double initialBalance, Double finalBalance){
        this.accountBalance = accountBalance;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }
}