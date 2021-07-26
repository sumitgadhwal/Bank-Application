package com.learning.sumit.bankApplication.service;


import com.learning.sumit.bankApplication.entities.AccountEntity;
import com.learning.sumit.bankApplication.entities.UserEntity;

import com.learning.sumit.bankApplication.exception.NotEnoughBalance;
import com.learning.sumit.bankApplication.repository.AccountRepository;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

/*
using spring runner, will load the complete application context
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
      AccountRepository accountRepository;

    private AccountEntity accountEntity;


    @Before
    public  void before()
    {
       System.out.println("Accounts before test:"+accountRepository.findAll().size());
    }

    @After
    public void after()
    {
        System.out.println("Accounts after test:"+accountRepository.findAll().size());
    }

    @Test@Order(1)
    public void testCreateAccount() throws InterruptedException {
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("skg@gmail.com");
        userEntity.setUserName("skg");
        userEntity.setMobileNumber(3568971);



        accountEntity=userService.createAccount(userEntity);
        accountEntity=userService.createAccount(userEntity);
        System.out.println(accountEntity);
        Assertions.assertNotNull(accountEntity.getAccountId());
        Assertions.assertNotNull(accountEntity.getBalance());
        Assertions.assertEquals(accountEntity.getUserEntity().getUserName(),"skg");
        Assertions.assertEquals(accountEntity.getUserEntity().getEmail(),"skg@gmail.com");

        System.out.println("Accounts during test:"+accountRepository.findAll().size());

    }

    @Test@Order(2)
    public void testDepositWithdraw() throws Exception {
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("skg@gmail.com");
        userEntity.setUserName("skg");
        userEntity.setMobileNumber(3568971);
        accountEntity=userService.createAccount(userEntity);

        userService.deposit(accountEntity.getAccountNumber(), BigDecimal.valueOf(500));
        Assertions.assertTrue(accountEntity.getBalance().compareTo(BigDecimal.valueOf(500)) == 0);
        System.out.println("deposited 500, current balance:"+accountEntity.getBalance());

        userService.withdraw(accountEntity.getAccountNumber(), BigDecimal.valueOf(200));
        System.out.println("withdraw 200, current balance:"+accountEntity.getBalance());
        Assertions.assertTrue(accountEntity.getBalance().compareTo(BigDecimal.valueOf(300)) == 0);

        Assert.assertTrue(userService.getBalance(accountEntity.getAccountNumber())
                .compareTo(BigDecimal.valueOf(300)) == 0);


        UserEntity userEntity1=userService.getUser(userEntity.getUserName(),userEntity.getEmail());
        Assertions.assertEquals(userEntity1.getUserName(),userEntity.getUserName());
        Assertions.assertEquals(userEntity1.getEmail(),userEntity.getEmail());
        Assertions.assertEquals(userEntity1.getMobileNumber(),userEntity.getMobileNumber());
        Assert.assertNotNull(userEntity1.getId());

    }

    @Test@Order(3)
    public void testNegativeFlow() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("skg@gmail.com");
        userEntity.setUserName("skg");
        userEntity.setMobileNumber(3568971);
        accountEntity = userService.createAccount(userEntity);


        try {
            userService.withdraw(accountEntity.getAccountNumber(), BigDecimal.valueOf(500));
        }
        catch (NotEnoughBalance e)
        {
            Assert.assertEquals(e.getMessage(),"Account doesn't have enough balance");
        }
    }
}
