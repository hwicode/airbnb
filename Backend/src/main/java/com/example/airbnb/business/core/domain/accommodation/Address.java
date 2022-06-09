package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Embeddable
public class Address {

    @Transient
    private static final String ADDRESS_DELIMETER = " ";

    @NotNull @NotEmpty
    private String gu;

    @NotNull @NotEmpty
    private String street;

    @NotNull @NotEmpty
    private String homeAddress;

    public Address(String gu, String street) {
        this.gu = gu;
        this.street = street;
        this.homeAddress = createHomeAddress(gu, street);
    }

    protected Address() {}

    private String createHomeAddress(String gu, String street) {
        return gu + ADDRESS_DELIMETER + street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(homeAddress, address.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeAddress);
    }
}
