package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.exception.UserAccountExistsException;
import com.wonderlabz.bankingaccountsystem.exception.UserNotFoundException;
import com.wonderlabz.bankingaccountsystem.exception.WithdrawalLimitException;
import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.repository.SavingsAccountRepository;
import com.wonderlabz.bankingaccountsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {

    private SystemUserService systemUserService;

    private UserRepository userRepository;

    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    public SavingsAccountService(SavingsAccountRepository savingsAccountRepository){
        this.savingsAccountRepository = savingsAccountRepository;
    }


    public SavingsAccount openSavingsAccount(User user, Double deposit){
        Long usrId = user.getId();
        /*boolean usrExists = savingsAccountRepository.findById(usrId).isPresent();

        if(!usrExists){
            throw new UserNotFoundException("The user Id number does not exist!");
        }*/
        if(user.getSavingsAccount() != null){
            throw new UserAccountExistsException("The user already has a savings account!");
        }
        SavingsAccount sa = new SavingsAccount();
        sa.setAccountNumber(generateAccountNumber());
        sa.setInitialBalance(deposit);
        sa.setFinalBalance(deposit);
        sa.setAccountBalance(deposit);
        sa.setTransactionAmount(deposit);
        sa.setTransactionType("Open Savings Account");
        savingsAccountRepository.save(sa);
        sa.setBankUsers(user);
        user.setSavingsAccount(sa);
        systemUserService.updateUser(user);
        return sa;

    }
//

    /*public SavingsAccount openSavingsAccount(Double initialDeposit){
        if(initialDeposit < 1000.00){
            throw new LowInitialDepositException("The amount entered is below R1000.00, please enter a minimum of R1000.00");
        }
        SavingsAccount sa = new SavingsAccount();
        sa.setInitialBalance(initialDeposit);
        sa.setFinalBalance(initialDeposit);
        sa.setAccountBalance(initialDeposit);
        sa.setAccountNumber(generateAccountNumber());
        sa.setTransactionType("Open Savings Account");
        return savingsAccountRepository.save(sa);
    }*/

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

    public User findUserById(Long Id) {
        return userRepository.findById(Id)
                .orElseThrow(() -> new UserNotFoundException("The user with Id number:" + Id + "dosn't exist."));

    }

    public static Long generateAccountNumber(){
        long lowerBound = 10000L;
        long higherBound = 100000L;
        long generatedLong = lowerBound + (long) (Math.random() * (higherBound - lowerBound));
        return generatedLong;
    }

    public void transferTrans(Double transfer){}
}
