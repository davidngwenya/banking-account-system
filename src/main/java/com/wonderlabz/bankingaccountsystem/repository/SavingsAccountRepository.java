package com.wonderlabz.bankingaccountsystem.repository;

import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import com.wonderlabz.bankingaccountsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
    //Optional <User> findByUserId(Long Id);
    //Optional findByUserId(Long Id);
    Optional<SavingsAccount> findSavingsAccountNumberByBankUsers(Long Id);
    Optional<SavingsAccount> findSavingsAccountByAccountNumber(Long accountNumber);

}
