package com.learning.sumit.bankApplication.entities;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="Accounts")
public class AccountEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
    private long accountId;
    
	@Column(name="accountnumber")
    private long accountNumber;
    
    @Column(name="balance")
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "userid" , referencedColumnName="id")
    private UserEntity userEntity;
    
    public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



}
