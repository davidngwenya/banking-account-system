package com.wonderlabz.bankingaccountsystem.repository;

import com.wonderlabz.bankingaccountsystem.model.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {
}
