package com.wonderlabz.bankingaccountsystem.controller;


import com.wonderlabz.bankingaccountsystem.model.CurrentAccount;
import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.service.CurrentAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bank/account/current")
public class CurrentAccountController {

    private CurrentAccountService currentAccountService;

    public CurrentAccountController(CurrentAccountService currentAccountService){
        this.currentAccountService = currentAccountService;
    }

    @GetMapping("/open/{deposit}")
    public ResponseEntity<CurrentAccount> openCurrentAccount(@PathVariable("deposit") Double deposit){
        CurrentAccount newCurrent = currentAccountService.openCurrentAccount(deposit);
        return new ResponseEntity<>(newCurrent, HttpStatus.CREATED);
    }

    @GetMapping("/deposit/{deposit}")
    public ResponseEntity<CurrentAccount> currentAccountDeposit(@PathVariable("deposit") Double deposit){
        CurrentAccount newDeposit = currentAccountService.currentAccountDeposit(deposit);
        return new ResponseEntity<>(newDeposit, HttpStatus.OK);
    }

    @GetMapping("/withdrawal/{withdrawal}")
    public ResponseEntity<CurrentAccount> savingsAccountWithdrawal(@PathVariable("withdrawal") Double withdrawal){
        CurrentAccount newWithdrawal = currentAccountService.currentAccountWithdrawal(withdrawal);
        return new ResponseEntity<>(newWithdrawal, HttpStatus.OK);
    }
}
