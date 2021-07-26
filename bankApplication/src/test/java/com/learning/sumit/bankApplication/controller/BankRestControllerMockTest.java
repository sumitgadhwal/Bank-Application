package com.learning.sumit.bankApplication.controller;


import com.learning.sumit.bankApplication.entities.AccountEntity;
import com.learning.sumit.bankApplication.entities.UserEntity;
import com.learning.sumit.bankApplication.service.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.Assert;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BankRestControllerMockTest {

    private UserService userService;

    /*

     */
    private BankRestController bankRestController;

    @Before
    public void setup()
    {
        userService=mock(UserService.class);
        bankRestController=new BankRestController(userService);
    }

    @Test
    public void controllerTest() throws Exception {
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail("skg@gmail.com");
        userEntity.setUserName("skg");
        userEntity.setMobileNumber(3568971);

        AccountEntity mockAccount=new AccountEntity();
        mockAccount.setAccountNumber(910000000008L);
        mockAccount.setBalance(BigDecimal.valueOf(0.0));
        mockAccount.setAccountId(1);
        mockAccount.setUserEntity(userEntity);

//        when(userService.createAccount(any(UserEntity.class))).thenReturn(
//                mockAccount
//        );

        doAnswer((Answer<AccountEntity>) invocationOnMock ->{
            return mockAccount;
        }).when(userService).createAccount(any(UserEntity.class));

        String message=bankRestController.createAccount(userEntity).toString();
        Assert.assertTrue(message.contains("Your Account Number is:"));
        Long accountNumber=Long.parseLong(message.split(":")[1].trim());
        System.out.println(message);
        Assert.assertTrue(accountNumber>=910000000000L);

        /*
        To verify how many times exactly mocked method was called
         */
        verify(userService,times(1)).createAccount(userEntity);


        when(userService.deposit(anyLong(),any(BigDecimal.class))).thenReturn(true);


    }



}
