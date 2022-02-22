package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.exception.OverDraftLimitException;
import com.wonderlabz.bankingaccountsystem.model.CurrentAccount;
import com.wonderlabz.bankingaccountsystem.repository.CurrentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentAccountService {

    private static final Double overdraftLimit = 100000.00;

    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    public CurrentAccountService(CurrentAccountRepository currentAccountRepository){
        this.currentAccountRepository = currentAccountRepository;
    }

    public CurrentAccount openCurrentAccount(Double initialDeposit){
        CurrentAccount sa = new CurrentAccount();
        sa.setInitialBalance(initialDeposit);
        sa.setFinalBalance(initialDeposit);
        sa.setAccountBalance(initialDeposit);
        sa.setTransactionType("Open Current Account");
        return currentAccountRepository.save(sa);
    }

    public CurrentAccount currentAccountDeposit(Double depositAmount){
        CurrentAccount sa = new CurrentAccount();
        Double initialBalance = sa.getAccountBalance();
        Double finalBalance = initialBalance + depositAmount;
        Double accountBalance = finalBalance;
        sa.setTransactionType("Current Account Deposit");
        sa.setInitialBalance(initialBalance);
        sa.setFinalBalance(finalBalance);
        sa.setAccountBalance(accountBalance);
        return currentAccountRepository.save(sa);
    }

    public CurrentAccount currentAccountWithdrawal(Double withdrawalAmount){
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
    }
}
