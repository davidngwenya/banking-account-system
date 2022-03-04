package com.wonderlabz.bankingaccountsystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "current_account")
public class CurrentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "date")
    private Date date;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_balance")
    private Double accountBalance;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(name = "final_balance")
    private Double finalBalance;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @OneToOne
    private User bankUsers;

    public CurrentAccount(){}

    public CurrentAccount(Date date, Long accountNumber, Double accountBalance, Double initialBalance, Double finalBalance, String transactionType,
                          Double transactionAmount){
        this.date = date;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getBankUsers() {
        return bankUsers;
    }

    public void setBankUsers(User bankUsers) {
        this.bankUsers = bankUsers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
