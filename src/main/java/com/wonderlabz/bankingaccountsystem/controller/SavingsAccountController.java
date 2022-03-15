package com.wonderlabz.bankingaccountsystem.controller;

import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import com.wonderlabz.bankingaccountsystem.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/accounts/savings")
public class SavingsAccountController {

    private SavingsAccountService savingsAccountService;


    @Autowired
    public SavingsAccountController(SavingsAccountService savingsAccountService){
        this.savingsAccountService = savingsAccountService;
    }

    @PostMapping("/open")
    public ResponseEntity<SavingsAccount> openSavingsAccount(@RequestBody User user, @RequestParam("deposit") Double deposit) throws Throwable{
        SavingsAccount newSavings = savingsAccountService.openSavingsAccount(user, deposit);
        return new ResponseEntity<SavingsAccount>(newSavings, HttpStatus.CREATED);
    }

    /*@PutMapping("/deposit/{deposit}")
    public ResponseEntity<SavingsAccount> savingsAccountDeposit(@PathVariable("deposit") Double deposit){
        SavingsAccount newDeposit = savingsAccountService.depositTrans(deposit);
        return new ResponseEntity<>(newDeposit, HttpStatus.OK);
    }*/

    @PutMapping("/deposit")
    public ResponseEntity<?> savingsAccountDeposit(@RequestBody User user, @RequestParam("deposit") Double deposit){
        /*SavingsAccount newDeposit = */
        savingsAccountService.depositTrans(user, deposit);
        return new ResponseEntity<SavingsAccount>(HttpStatus.OK);
    }

    /*@GetMapping("/withdrawal/{withdrawal}")
    public ResponseEntity<SavingsAccount> savingsAccountWithdrawal(@PathVariable("withdrawal") Double withdrawal){
        SavingsAccount newWithdrawal = savingsAccountService.withdrawalTrans(withdrawal);
        return new ResponseEntity<>(newWithdrawal, HttpStatus.OK);
    }*/
    @PutMapping("/withdrawal")
    public ResponseEntity<?> savingsAccountWithdrawal(@RequestBody User user, @RequestParam("withdrawal") Double withdrawal){
        /*SavingsAccount newDeposit = */
        savingsAccountService.withdrawalTrans(user, withdrawal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> savingsToCurrentTransfer(@RequestBody User user, @RequestParam("transfer") Double transfer){
        /*SavingsAccount newTransfer = */savingsAccountService.savingsToCurrentTransfer(user, transfer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SavingsAccount>> savingsAccountTransHistory(){
        List<SavingsAccount> transHistory = savingsAccountService.transactionHistory();
        return new ResponseEntity<List<SavingsAccount>>(transHistory, HttpStatus.OK);
    }
}
