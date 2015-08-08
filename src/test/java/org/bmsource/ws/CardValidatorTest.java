package org.bmsource.ws;

import org.junit.Assert;
import org.junit.Test;

public class CardValidatorTest
{
    @Test
    public void shouldCheckCreditCardValidity() throws CardValidationException
    {
        CardValidator cardValidator = new CardValidator();
        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
        Assert.assertTrue("Credit card should be valid", cardValidator.validate(creditCard));
        creditCard.setNumber("12341233");
        Assert.assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));
    }
}
