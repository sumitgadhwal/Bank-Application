package com.learning.sumit.bankApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.sumit.bankApplication.entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer>{
	AccountEntity findByAccountNumber(long accountNumber);

}
