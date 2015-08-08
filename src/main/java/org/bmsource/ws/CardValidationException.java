package org.bmsource.ws;

import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebFault;

@WebFault(name = "CardValidationFault")
public class CardValidationException extends Exception
{
    public CardValidationException()
    {
        super();
    }

    public CardValidationException(String message)
    {
        super(message);
    }

    public CardValidationException(SOAPFault fault)
    {
        super(fault.toString());
    }
}
