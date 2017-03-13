package com.company.ceae.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@Listeners("ceae_CustomerCreatedEntityListener")
@NamePattern("%s, %s|name,firstName")
@Table(name = "CEAE_CUSTOMER")
@Entity(name = "ceae$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = -2008688419278385187L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "CUSTOMER_TYPE")
    protected String customerType;

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType == null ? null : customerType.getId();
    }

    public CustomerType getCustomerType() {
        return customerType == null ? null : CustomerType.fromId(customerType);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }


}