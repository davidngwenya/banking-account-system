package com.wonderlabz.bankingaccountsystem.controller;

import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/account/savings")
public class SavingsAccountController {

    private SavingsAccountService savingsAccountService;

    @Autowired
    public SavingsAccountController(SavingsAccountService savingsAccountService){
        this.savingsAccountService = savingsAccountService;
    }

    @PostMapping("/open/{deposit}")
    public ResponseEntity<SavingsAccount> openSavingsAccount(@RequestBody Double deposit){
        SavingsAccount newSavings = savingsAccountService.openSavingsAccount(deposit);
        return new ResponseEntity<>(newSavings, HttpStatus.CREATED);
    }

    @PutMapping("/deposit")
    public ResponseEntity<SavingsAccount> savingsAccountDeposit(@RequestBody Double deposit){
        SavingsAccount newDeposit = savingsAccountService.depositTrans(deposit);
        return new ResponseEntity<>(newDeposit, HttpStatus.OK);
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<SavingsAccount> savingsAccountWithdrawal(@RequestBody Double withdrawal){
        SavingsAccount newWithdrawal = savingsAccountService.withdrawalTrans(withdrawal);
        return new ResponseEntity<>(newWithdrawal, HttpStatus.OK);
    }
}
