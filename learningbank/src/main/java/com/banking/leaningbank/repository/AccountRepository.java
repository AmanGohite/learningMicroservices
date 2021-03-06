package com.banking.leaningbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.leaningbank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByCustomerId(Integer customerId);
}