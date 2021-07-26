package com.learning.sumit.bankApplication.controller;


import com.learning.sumit.bankApplication.entities.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankRestControllerTest {



    @Autowired
    private  BankRestController bankRestController;


    @Test
    public void controllerTest() throws Exception {
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("skg@gmail.com");
        userEntity.setUserName("skg");
        userEntity.setMobileNumber(3568971);

        String message=bankRestController.createAccount(userEntity).toString();
        Assert.assertTrue(message.contains("Your Account Number is:"));
        Long accountNumber=Long.parseLong(message.split(":")[1].trim());
        System.out.println(message);
        Assert.assertTrue(accountNumber>=910000000000L);

        Assert.assertTrue(bankRestController.deposit(accountNumber, BigDecimal.valueOf(500)));
        Assert.assertTrue(bankRestController.getBalance(accountNumber).compareTo(BigDecimal.valueOf(500))==0);

        Assert.assertTrue(bankRestController.withdraw(accountNumber, BigDecimal.valueOf(200)));
        Assert.assertTrue(bankRestController.getBalance(accountNumber).compareTo(BigDecimal.valueOf(300))==0);

        UserEntity userEntity1=bankRestController.getUser(userEntity.getUserName(), userEntity.getEmail());
        Assertions.assertEquals(userEntity1.getUserName(),userEntity.getUserName());
        Assertions.assertEquals(userEntity1.getEmail(),userEntity.getEmail());
        Assertions.assertEquals(userEntity1.getMobileNumber(),userEntity.getMobileNumber());
        org.junit.Assert.assertNotNull(userEntity1.getId());

    }
}
