package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.exception.WithdrawalLimitException;
import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.repository.SavingsAccountRepository;
import com.wonderlabz.bankingaccountsystem.exception.LowInitialDepositException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {

    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    public SavingsAccountService(SavingsAccountRepository savingsAccountRepository){
        this.savingsAccountRepository = savingsAccountRepository;
    }

    public SavingsAccount openSavingsAccount(Double initialDeposit){
        if(initialDeposit < 1000.00){
            throw new LowInitialDepositException("The amount entered is below R1000.00, please enter a minimum of R1000.00");
        }
        SavingsAccount sa = new SavingsAccount();
        sa.setInitialBalance(initialDeposit);
        sa.setFinalBalance(initialDeposit);
        sa.setAccountBalance(initialDeposit);
        sa.setTransactionType("Open Savings Account");
        return savingsAccountRepository.save(sa);
    }

    public SavingsAccount depositTrans(Double deposit){
        SavingsAccount sa = new SavingsAccount();
        Double initialBalance = sa.getAccountBalance();
        Double finalBalance = initialBalance + deposit;
        Double accountBalance = finalBalance;
        sa.setTransactionType("Bank Deposit");
        sa.setInitialBalance(initialBalance);
        sa.setFinalBalance(finalBalance);
        sa.setAccountBalance(accountBalance);
        return savingsAccountRepository.save(sa);
    }

    public SavingsAccount withdrawalTrans(Double withdrawal){
        SavingsAccount sa = new SavingsAccount();
        Double initialBalance = sa.getAccountBalance();
        Double finalBalance = sa.getAccountBalance() - withdrawal;
            if(finalBalance < 1000) throw new WithdrawalLimitException("The withdrawal amount exceeds the minimum account balance of R1000");
        Double accountBalance = finalBalance;
        sa.setTransactionType("Bank Withdrawal");
        sa.setInitialBalance(initialBalance);
        sa.setFinalBalance(finalBalance);
        sa.setAccountBalance(accountBalance);
        return savingsAccountRepository.save(sa);
    }

    public void transferTrans(Double transfer){}
}
