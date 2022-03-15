package com.wonderlabz.bankingaccountsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bank_users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "cell_number")
    private String cellNumber;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    /*@JoinColumns({@JoinColumn(name = "Savings_Account_Id", referencedColumnName="Id"),
                  @JoinColumn(name = "Savings_Account_Number", referencedColumnName="account_number"),
                  @JoinColumn(name = "Savings_Account_Balance", referencedColumnName="account_balance") })*/
    @JoinColumn(name = "Savings_Account_Id", referencedColumnName="Id")
    @JsonManagedReference
    private SavingsAccount savingsAccount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    /*@JoinColumns({@JoinColumn(name = "Current_Account_Id", referencedColumnName="Id"),
            @JoinColumn(name = "Current_Account_Number", referencedColumnName="account_number"),
            @JoinColumn(name = "Current_Account_Balance", referencedColumnName="account_balance") })*/
    @JoinColumn(name = "Current_Account_Id", referencedColumnName="Id")
    @JsonManagedReference
    private CurrentAccount currentAccount;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Current_Account_Id", referencedColumnName="Id")
    private CurrentAccount currentAccount;*/

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Current_Account_Id", referencedColumnName = "Id")
    private CurrentAccount currentAccount;*/

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Savings_Account_Id", referencedColumnName = "Id")
    private SavingsAccount savingsAccount;*/

    public User(){}

    public User(String firstName, String lastName, String address, String cellNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cellNumber = cellNumber;
        this.email = email;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        userId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
