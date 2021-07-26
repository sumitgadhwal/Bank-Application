package com.learning.sumit.bankApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.sumit.bankApplication.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
	UserEntity findByUserNameAndEmail(String userName,String userPassword);

}
