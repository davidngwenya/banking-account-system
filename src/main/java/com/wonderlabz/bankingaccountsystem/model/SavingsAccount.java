package com.wonderlabz.bankingaccountsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "savings_account")
public class SavingsAccount {

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

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber", referencedColumnName = "Id")
    private User users;*/

    public SavingsAccount(){}

    public SavingsAccount(Long accountNumber, Double accountBalance, Double initialBalance, Double finalBalance){
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

    /*public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }*/

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
}
