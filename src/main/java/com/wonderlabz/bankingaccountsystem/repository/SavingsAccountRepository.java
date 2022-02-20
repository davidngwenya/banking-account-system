package com.wonderlabz.bankingaccountsystem.repository;

import com.wonderlabz.bankingaccountsystem.model.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
