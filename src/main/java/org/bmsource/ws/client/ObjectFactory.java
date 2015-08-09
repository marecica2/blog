package org.bmsource.ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.bmsource.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory
{

    private final static QName _ValidateResponse_QNAME = new QName("http://ws.bmsource.org/", "validateResponse");
    private final static QName _CreditCard_QNAME = new QName("http://ws.bmsource.org/", "creditCard");
    private final static QName _CardValidationFault_QNAME = new QName("http://ws.bmsource.org/", "CardValidationFault");
    private final static QName _Validate_QNAME = new QName("http://ws.bmsource.org/", "validate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.bmsource.ws
     * 
     */
    public ObjectFactory()
    {
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard()
    {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link CardValidationException }
     * 
     */
    public CardValidationException createCardValidationException()
    {
        return new CardValidationException();
    }

    /**
     * Create an instance of {@link ValidateResponse }
     * 
     */
    public ValidateResponse createValidateResponse()
    {
        return new ValidateResponse();
    }

    /**
     * Create an instance of {@link Validate }
     * 
     */
    public Validate createValidate()
    {
        return new Validate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bmsource.org/", name = "validateResponse")
    public JAXBElement<ValidateResponse> createValidateResponse(ValidateResponse value)
    {
        return new JAXBElement<ValidateResponse>(_ValidateResponse_QNAME, ValidateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bmsource.org/", name = "creditCard")
    public JAXBElement<CreditCard> createCreditCard(CreditCard value)
    {
        return new JAXBElement<CreditCard>(_CreditCard_QNAME, CreditCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bmsource.org/", name = "CardValidationFault")
    public JAXBElement<CardValidationException> createCardValidationFault(CardValidationException value)
    {
        return new JAXBElement<CardValidationException>(_CardValidationFault_QNAME, CardValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Validate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bmsource.org/", name = "validate")
    public JAXBElement<Validate> createValidate(Validate value)
    {
        return new JAXBElement<Validate>(_Validate_QNAME, Validate.class, null, value);
    }

}
