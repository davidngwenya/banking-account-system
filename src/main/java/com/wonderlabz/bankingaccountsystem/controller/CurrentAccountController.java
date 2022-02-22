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

    @PostMapping("/open")
    public ResponseEntity<CurrentAccount> openCurrentAccount(@RequestBody Double deposit){
        CurrentAccount newCurrent = currentAccountService.openCurrentAccount(deposit);
        return new ResponseEntity<>(newCurrent, HttpStatus.CREATED);
    }

    @PutMapping("/deposit")
    public ResponseEntity<CurrentAccount> currentAccountDeposit(@RequestBody Double deposit){
        CurrentAccount newDeposit = currentAccountService.currentAccountDeposit(deposit);
        return new ResponseEntity<>(newDeposit, HttpStatus.OK);
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<CurrentAccount> savingsAccountWithdrawal(@RequestBody Double withdrawal){
        CurrentAccount newWithdrawal = currentAccountService.currentAccountWithdrawal(withdrawal);
        return new ResponseEntity<>(newWithdrawal, HttpStatus.OK);
    }
}
