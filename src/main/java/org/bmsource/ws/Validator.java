package org.bmsource.ws;

import javax.jws.WebService;

@WebService
public interface Validator
{
    public boolean validate(CreditCard creditCard) throws CardValidationException;
}