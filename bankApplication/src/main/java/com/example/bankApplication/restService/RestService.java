package com.example.bankApplication.restService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.bankApplication.entities.UserEntity;
import com.example.bankApplication.service.UserService;

@RestController
public class RestService {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/user/get-user/{userName}&{userEmail}",method = RequestMethod.GET)
	public UserEntity getUser(@PathVariable String userName, @PathVariable String userEmail) throws Exception
	{
		return userService.getUser(userName,userEmail);
	}
	@RequestMapping(value="/bank/create-account/",method = RequestMethod.POST)
    public UserEntity createAccount(@RequestBody UserEntity userDetails)
    {
		//System.out.println(userDetails.getMobileNumber());
        return userService.createAccount(userDetails);
    }
	@RequestMapping(value="/bank/get-balance/{accountNumber}",method = RequestMethod.GET)
	public BigDecimal getBalance(@PathVariable long accountNumber)throws Exception
	{
		return userService.getBalance(accountNumber);
	}
	@RequestMapping(value="/bank/deposit/{accountNumber}&{money}",method = RequestMethod.GET)
	public boolean deposit(@PathVariable long accountNumber,@PathVariable BigDecimal money )throws Exception
	{
		return userService.deposit(accountNumber,money);
	}
	@RequestMapping(value="/bank/withdraw/{accountNumber}&{money}",method = RequestMethod.GET)
	public boolean withdraw(@PathVariable long accountNumber,@PathVariable BigDecimal money ) throws Exception
	{

		return userService.withdraw(accountNumber,money);

	}
	

}
