package com.learning.sumit.bankApplication.service;

import com.learning.sumit.bankApplication.entities.AccountEntity;
import com.learning.sumit.bankApplication.entities.UserEntity;
import com.learning.sumit.bankApplication.repository.AccountRepository;
import com.learning.sumit.bankApplication.repository.UserRepository;
import com.learning.sumit.bankApplication.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTestNgTest {

    private  UserService userService;
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @BeforeClass
    public  void setup()
    {
        userRepository=mock(UserRepository.class);
        accountRepository= mock(AccountRepository.class);
        userService=new UserServiceImpl(userRepository,accountRepository);
    }

    @Test
    public void testCreateAccount() throws InterruptedException {
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

        verify(userRepository,times(1)).save(mockUser);
        verify(accountRepository,times(1)).save(any());


    }

}
