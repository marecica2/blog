package org.bmsource.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GroupProperty
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String value;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "GroupProperty [id=" + id + ", name=" + name + ", value=" + value + "]";
    }
}
