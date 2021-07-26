package com.learning.sumit.bankApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotEnoughBalance extends RuntimeException 
{
    public NotEnoughBalance(String exception) {
        super(exception);
    }
}