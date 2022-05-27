package com.example.airbnb.business.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Getter
@Embeddable
public class Address {

    @Transient
    private static final String ADDRESS_DELIMETER = " ";
    private String gu;
    private String street;
    private String zipCode;
    private String homeAddress;

    public Address(String gu, String street, String zipCode) {
        this.gu = gu;
        this.street = street;
        this.zipCode = zipCode;
        this.homeAddress = createHomeAddress(gu, street);
    }

    protected Address() {}

    private String createHomeAddress(String gu, String street) {
        return gu + ADDRESS_DELIMETER + street;
    }
}
