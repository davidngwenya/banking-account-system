package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.repository.SavingsAccountRepository;
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
        if(initialDeposit <= 1000.00){
            throw LowInitialDepositException("The amount entered is below R1000.00, please enter a minimum of R1000.00");
        }
        SavingsAccount sa = new SavingsAccount();
        sa.setAccountBalance();
    }

    public void depositTrans(Double deposit){}

    public void withdrawalTrans(Double withdrawal){}

    public void transferTrans(Double transfer){}
}
