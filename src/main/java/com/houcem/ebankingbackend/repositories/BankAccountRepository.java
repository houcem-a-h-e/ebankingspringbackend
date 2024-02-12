package com.houcem.ebankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houcem.ebankingbackend.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,String>{

}
