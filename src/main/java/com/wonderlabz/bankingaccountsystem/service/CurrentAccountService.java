package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.exception.*;
import com.wonderlabz.bankingaccountsystem.model.CurrentAccount;
import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.repository.CurrentAccountRepository;
import com.wonderlabz.bankingaccountsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentAccountService {

    private static final Double overdraftLimit = -100000.00;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private UserRepository userRepository;

    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    public CurrentAccountService(CurrentAccountRepository currentAccountRepository){
        this.currentAccountRepository = currentAccountRepository;
    }


    public CurrentAccount openCurrentAccount(User user, Double deposit) throws Throwable {


        boolean accExists = user.getCurrentAccount() != null;
        /*if(accNum != null){*/
        if(accExists){
            throw new UserAccountExistsException("The user already has a current account!");
        }

        //user.getCurrentAccount().setAccountNumber(generateAccountNumber());
        CurrentAccount sa = new CurrentAccount();
        sa.setDate();
        sa.setAccountNumber(generateAccountNumber());
        sa.setInitialBalance(deposit);
        sa.setFinalBalance(deposit);
        sa.setAccountBalance(deposit);
        sa.setTransactionAmount(deposit);
        sa.setTransactionType("Open Current Account");
        sa.setBankUsers(user);
        currentAccountRepository.save(sa);
        user.setCurrentAccount(sa);
        systemUserService.updateUser(user);
        return sa;
    }

    /*public CurrentAccount currentAccountDeposit(Double depositAmount){
        CurrentAccount sa = new CurrentAccount();
        Double initialBalance = sa.getAccountBalance();
        Double finalBalance = initialBalance + depositAmount;
        Double accountBalance = finalBalance;
        sa.setTransactionType("Current Account Deposit");
        sa.setInitialBalance(initialBalance);
        sa.setFinalBalance(finalBalance);
        sa.setAccountBalance(accountBalance);
        return currentAccountRepository.save(sa);
    }*/

    public void currentAccountDeposit(User user, Double deposit){
        //Long usrId = user.getId();
        Double currentBalance = user.getCurrentAccount().getAccountBalance();
        user.getCurrentAccount().setInitialBalance(currentBalance);
        Double newBalance = currentBalance + deposit;
        user.getCurrentAccount().setFinalBalance(newBalance);
        user.getCurrentAccount().setAccountBalance(newBalance);
        user.getCurrentAccount().setTransactionAmount(deposit);
        user.getCurrentAccount().setTransactionType("Current Account Deposit");
        user.getCurrentAccount().setDate();
        systemUserService.updateUser(user);
        //return currentAccountRepository.save(user.getCurrentAccount());
    }

    /*public CurrentAccount currentAccountWithdrawal(Double withdrawalAmount){
        CurrentAccount sa = new CurrentAccount();
        Double initialBalance = sa.getAccountBalance();
        Double finalBalance = initialBalance - withdrawalAmount;
        Double overdraft = -(finalBalance + overdraftLimit);
        if(finalBalance < overdraft){
            throw new OverDraftLimitException("The withdrawal attempt exceeds the overdraft limit");
        }
        Double accountBalance = finalBalance;
        sa.setTransactionType("Current Account Withdrawal");
        sa.setInitialBalance(initialBalance);
        sa.setFinalBalance(finalBalance);
        sa.setAccountBalance(accountBalance);
        return currentAccountRepository.save(sa);
    }*/

    public void withdrawalTrans(User user,  Double withdrawal){
        Double currentBalance = user.getCurrentAccount().getAccountBalance();
        user.getCurrentAccount().setInitialBalance(currentBalance);

        Double newBalance = currentBalance - withdrawal;

        if(newBalance < overdraftLimit){
            throw new OverDraftLimitException("The withdrawal attempt exceeds the overdraft limit");
        }
        user.getCurrentAccount().setFinalBalance(newBalance);
        user.getCurrentAccount().setAccountBalance(newBalance);
        user.getCurrentAccount().setTransactionAmount(withdrawal);
        user.getCurrentAccount().setTransactionType("Current Account Withdrawal");
        user.getCurrentAccount().setDate();
        systemUserService.updateUser(user);
        //return currentAccountRepository.save(user.getCurrentAccount());
    }

    public void currentToSavingsTransfer(User user, Double transfer){
        Double oldCurrAccBalance = user.getCurrentAccount().getAccountBalance();
        Double newCurrAccBalance = oldCurrAccBalance - transfer;
        if(newCurrAccBalance < overdraftLimit){
            throw new OverDraftLimitException("The transfer amount exceeds the overdraft limit");
        }

        Double oldSavAccBalance = user.getSavingsAccount().getAccountBalance();
        Double newSavAccBalance = user.getSavingsAccount().getAccountBalance() + transfer;

        user.getCurrentAccount().setInitialBalance(oldCurrAccBalance);
        user.getCurrentAccount().setFinalBalance(newCurrAccBalance);
        user.getCurrentAccount().setAccountBalance(newCurrAccBalance);
        user.getCurrentAccount().setTransactionAmount(transfer);
        user.getCurrentAccount().setTransactionType("Intra-Account Transfer To Savings Account");
        user.getCurrentAccount().setDate();

        user.getSavingsAccount().setInitialBalance(oldSavAccBalance);
        user.getSavingsAccount().setFinalBalance(newSavAccBalance);
        user.getSavingsAccount().setAccountBalance(newSavAccBalance);
        user.getSavingsAccount().setTransactionAmount(transfer);
        user.getSavingsAccount().setTransactionType("Intra-Account Transfer From Current Account");
        user.getSavingsAccount().setDate();

        userRepository.save(user);

        //return user.getCurrentAccount();
    }

    public List<CurrentAccount> transactionHistory(){
        return currentAccountRepository.findAll();
    }

    public static Long generateAccountNumber(){
        long lowerBound = 10000L;
        long higherBound = 100000L;
        long generatedLong = lowerBound + (long) (Math.random() * (higherBound - lowerBound));
        return generatedLong;
    }

}
