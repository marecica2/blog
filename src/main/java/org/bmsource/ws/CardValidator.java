package org.bmsource.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface = "org.bmsource.ws.Validator")
public class CardValidator implements Validator
{
    @Override
    public boolean validate(CreditCard creditCard) throws CardValidationException
    {
        Character lastDigit = creditCard.getNumber().charAt(
                creditCard.getNumber().length() - 1);
        if (Integer.parseInt(lastDigit.toString()) % 2 == 0)
        {
            return true;
        } else
        {
            return false;
            //throw new CardValidationException("The credit card number is invalid");
        }
    }

    @WebResult(name = "IsValid")
    @WebMethod(operationName = "ValidateCreditCardNumber")
    public void validate(@WebParam(name = "Credit-Card-Number") String creditCardNumber)
    {
        System.out.println("validate");
    }

    @WebMethod(exclude = true)
    public void validate(Long creditCardNumber)
    {
        System.out.println("validate");
    }
}