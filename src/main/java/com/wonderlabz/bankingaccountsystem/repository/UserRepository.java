package com.wonderlabz.bankingaccountsystem.repository;

import com.wonderlabz.bankingaccountsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
