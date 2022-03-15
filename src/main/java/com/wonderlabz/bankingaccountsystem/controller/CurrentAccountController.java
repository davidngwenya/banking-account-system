package com.wonderlabz.bankingaccountsystem.controller;


import com.wonderlabz.bankingaccountsystem.model.CurrentAccount;
import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.service.CurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bank/accounts/current")
public class CurrentAccountController {

    private CurrentAccountService currentAccountService;

    @Autowired
    public CurrentAccountController(CurrentAccountService currentAccountService){
        this.currentAccountService = currentAccountService;
    }

    @PostMapping("/open")
    public ResponseEntity<CurrentAccount> openCurrentAccount(@RequestBody User user, @RequestParam("deposit") Double deposit) throws Throwable{
        CurrentAccount newCurrent = currentAccountService.openCurrentAccount(user, deposit);
        return new ResponseEntity<CurrentAccount>(newCurrent, HttpStatus.CREATED);
    }

    @PutMapping("/deposit")
    public ResponseEntity<?> currentAccountDeposit(@RequestBody User user, @RequestParam("deposit") Double deposit){
        /*CurrentAccount newDeposit = */
        currentAccountService.currentAccountDeposit(user, deposit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<?> currentAccountWithdrawal(@RequestBody User user, @RequestParam("withdrawal") Double withdrawal){
        /*CurrentAccount newWithdrawal = */
        currentAccountService.withdrawalTrans(user, withdrawal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> currentToSavingsTransfer(@RequestBody User user, @RequestParam("transfer") Double transfer){
        /*CurrentAccount newTransfer = */
        currentAccountService.currentToSavingsTransfer(user, transfer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrentAccount>> currentAccountTransHistory(){
        List<CurrentAccount> transHistory = currentAccountService.transactionHistory();
        return new ResponseEntity<List<CurrentAccount>>(transHistory, HttpStatus.OK);
    }
}
