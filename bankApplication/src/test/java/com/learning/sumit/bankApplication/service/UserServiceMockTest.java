package com.learning.sumit.bankApplication.service;

import com.learning.sumit.bankApplication.entities.AccountEntity;
import com.learning.sumit.bankApplication.entities.UserEntity;
import com.learning.sumit.bankApplication.exception.NotEnoughBalance;
import com.learning.sumit.bankApplication.repository.AccountRepository;
import com.learning.sumit.bankApplication.repository.UserRepository;
import com.learning.sumit.bankApplication.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
using MockitoJUnitRunner and @Mock annotation
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceMockTest {

     private static UserService userService;
     @Mock
     static AccountRepository accountRepository;

    @Mock
    static UserRepository userRepository;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        userService= new UserServiceImpl(userRepository,accountRepository);
    }

    @Test
    public void testCreateAccount() throws Exception {
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("skg@gmail.com");
        userEntity.setUserName("skg");
        userEntity.setMobileNumber(3568971);



        UserEntity mockUser=userEntity;
        mockUser.setId(1);
        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(
                mockUser
        );

        AccountEntity mockEntity=new AccountEntity();
        mockEntity.setAccountId(3);
        mockEntity.setUserEntity(mockUser);
        mockEntity.setAccountNumber(9100000007L);
        mockEntity.setBalance(BigDecimal.valueOf(200));

        Mockito.when(accountRepository.save(any(AccountEntity.class))).thenReturn(
                mockEntity
        );

        AccountEntity accountEntity=userService.createAccount(userEntity);
        System.out.println(accountEntity);
        Assertions.assertNotNull(accountEntity.getAccountId());
        Assertions.assertNotNull(accountEntity.getBalance());
        Assertions.assertEquals(accountEntity.getUserEntity().getUserName(),"skg");
        Assertions.assertEquals(accountEntity.getUserEntity().getEmail(),"skg@gmail.com");

        when(userRepository.findByUserNameAndEmail(anyString(),anyString())).thenReturn(
                mockUser
        );
        accountEntity=userService.createAccount(userEntity);


        verify(userRepository,times(1)).save(mockUser);
        verify(accountRepository,times(2)).save(any());




        when(accountRepository.findByAccountNumber(anyLong())).thenReturn(
                mockEntity
        );
        Assertions.assertTrue(userService.deposit(mockEntity.getAccountNumber(),BigDecimal.valueOf(200)));
        Assertions.assertTrue(userService.withdraw(mockEntity.getAccountNumber(),BigDecimal.valueOf(150)));

        boolean flag=false;
        try {
            userService.withdraw(mockEntity.getAccountNumber(), BigDecimal.valueOf(500));
        }
        catch (NotEnoughBalance e)
        {
            flag=true;
        }
        Assertions.assertTrue(flag);

        System.out.println(mockEntity);
        Assertions.assertTrue(userService.getBalance(mockEntity.getAccountNumber()).compareTo(mockEntity.getBalance())==0);

        verify(accountRepository,times(4)).findByAccountNumber(anyLong());


    }

}
