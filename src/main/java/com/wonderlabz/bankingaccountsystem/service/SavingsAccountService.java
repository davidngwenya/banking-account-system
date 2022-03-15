package com.wonderlabz.bankingaccountsystem.service;

import com.wonderlabz.bankingaccountsystem.exception.*;
import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.repository.SavingsAccountRepository;
import com.wonderlabz.bankingaccountsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingsAccountService {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private UserRepository userRepository;

    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    public SavingsAccountService(SavingsAccountRepository savingsAccountRepository){
        this.savingsAccountRepository = savingsAccountRepository;
    }


    public SavingsAccount openSavingsAccount(User user, Double deposit) throws Throwable {

        boolean accExists = user.getSavingsAccount() != null;

        if(accExists){
            throw new UserAccountExistsException("The user already has a savings account!");
        }

        if(deposit < 1000.00){
            throw new LowInitialDepositException("The amount entered is below R1000.00, please enter a minimum of R1000.00");
        }

        SavingsAccount sa = new SavingsAccount();
        sa.setDate();
        sa.setAccountNumber(generateAccountNumber());
        sa.setInitialBalance(deposit);
        sa.setFinalBalance(deposit);
        sa.setAccountBalance(deposit);
        sa.setTransactionAmount(deposit);
        sa.setTransactionType("Open Savings Account");
        sa.setBankUsers(user);
        savingsAccountRepository.save(sa);
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

    /*public SavingsAccount depositTrans(Double deposit){
        SavingsAccount sa = new SavingsAccount();
        Double initialBalance = sa.getAccountBalance();
        Double finalBalance = initialBalance + deposit;
        Double accountBalance = finalBalance;
        sa.setTransactionType("Bank Deposit");
        sa.setInitialBalance(initialBalance);
        sa.setFinalBalance(finalBalance);
        sa.setAccountBalance(accountBalance);
        return savingsAccountRepository.save(sa);
    }*/

    public void depositTrans(User user, Double deposit){
        //Long usrId = user.getId();
        Double currentBalance = user.getSavingsAccount().getAccountBalance();
        user.getSavingsAccount().setInitialBalance(currentBalance);
        Double newBalance = currentBalance + deposit;
        user.getSavingsAccount().setFinalBalance(newBalance);
        user.getSavingsAccount().setAccountBalance(newBalance);
        user.getSavingsAccount().setTransactionAmount(deposit);
        user.getSavingsAccount().setTransactionType("Savings Account Deposit");
        user.getSavingsAccount().setDate();
        systemUserService.updateUser(user);
        //return savingsAccountRepository.save(user.getSavingsAccount());
    }

    /*public SavingsAccount withdrawalTrans(Double withdrawal){
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
    }*/

    public void withdrawalTrans(User user,  Double withdrawal){
        Double currentBalance = user.getSavingsAccount().getAccountBalance();
        user.getSavingsAccount().setInitialBalance(currentBalance);
        Double newBalance = currentBalance - withdrawal;
        if(newBalance < 1000){
            throw new WithdrawalLimitException("The withdrawal amount exceeds the minimum account balance of R1000");
        }
        user.getSavingsAccount().setFinalBalance(newBalance);
        user.getSavingsAccount().setAccountBalance(newBalance);
        user.getSavingsAccount().setTransactionAmount(withdrawal);
        user.getSavingsAccount().setTransactionType("Savings Account Withdrawal");
        user.getSavingsAccount().setDate();
        systemUserService.updateUser(user);
        //return savingsAccountRepository.save(user.getSavingsAccount());
    }

    public List<SavingsAccount> transactionHistory(){
        return savingsAccountRepository.findAll();
    }

    public void savingsToCurrentTransfer(User user, Double transfer){
        Double oldSavAccBalance = user.getSavingsAccount().getAccountBalance();
        Double newSavAccBalance = oldSavAccBalance - transfer;
        if(newSavAccBalance < 1000){
            throw new TransferAmountException("The transfer amount exceeds the minimum account balance of R1000");
        }
        Double oldCurrAccBalance = user.getCurrentAccount().getAccountBalance();
        Double newCurrAccBalance = user.getCurrentAccount().getAccountBalance() + transfer;

        user.getSavingsAccount().setInitialBalance(oldSavAccBalance);
        user.getSavingsAccount().setFinalBalance(newSavAccBalance);
        user.getSavingsAccount().setAccountBalance(newSavAccBalance);
        user.getSavingsAccount().setTransactionAmount(transfer);
        user.getSavingsAccount().setTransactionType("Intra-Account Transfer To Current Account");
        user.getSavingsAccount().setDate();

        user.getCurrentAccount().setInitialBalance(oldCurrAccBalance);
        user.getCurrentAccount().setFinalBalance(newCurrAccBalance);
        user.getCurrentAccount().setAccountBalance(newCurrAccBalance);
        user.getCurrentAccount().setTransactionAmount(transfer);
        user.getCurrentAccount().setTransactionType("Intra-Account Transfer From Savings Account");
        user.getCurrentAccount().setDate();

        userRepository.save(user);

        //return user.getSavingsAccount();
    }

    /*public SavingsAccount findSavingsAccountByBankUserId(Long Id) throws Throwable {
        return savingsAccountRepository.findSavingsAccountNumberByBankUsers(Id)
                .orElseThrow(() -> new UserAccountExistsException("The user already has a savings account!"));
    }*/

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
}
