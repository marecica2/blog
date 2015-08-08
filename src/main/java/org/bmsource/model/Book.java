package org.bmsource.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({
        @NamedQuery(name = "Book.findAllBooks", query = "SELECT b FROM Book b"),
        @NamedQuery(name = "`Book.findBookH2G2", query = "SELECT b FROM Book b WHERE b.title ='H2G2'")
})
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable
{
    public static final String FIND_ALL = "Book.findAllBooks";

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false)
    @XmlAttribute(required = true)
    private String title;

    @NotNull
    @Min(2)
    private Float price;

    @Size(max = 2000)
    @Column(length = 2000)
    private String description;

    private String number;

    public Book()
    {
    }

    public Book(String title, String description, Float price, String number)
    {
        super();
        this.title = title;
        this.price = price;
        this.description = description;
        this.number = number;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Float getPrice()
    {
        return price;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return "Book [id=" + id + ", title=" + title + ", price=" + price + ", number=" + number + "]";
    }

}
