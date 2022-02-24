package com.wonderlabz.bankingaccountsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "current_account")
public class CurrentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

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

    /*@OneToOne(mappedBy = "current_account")
    private User bankUsers;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Id", referencedColumnName = "Id")
    private User bankUsers;

    public CurrentAccount(){}

    public CurrentAccount(Long accountNumber, Double accountBalance, Double initialBalance, Double finalBalance){
        this.accountNumber = accountNumber;
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
}
