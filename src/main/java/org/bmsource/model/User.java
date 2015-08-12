package org.bmsource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"user\"")
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "{validation.required}")
    @Column(nullable = false)
    private String login;

    @NotNull(message = "{validation.required}")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "{validation.required}")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "{validation.required}")
    @Column(nullable = false)
    private String password;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
