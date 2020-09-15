package com.example.bankApplication.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankApplication.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
	UserEntity findByUserNameAndEmail(String userName,String userPassword);

}
