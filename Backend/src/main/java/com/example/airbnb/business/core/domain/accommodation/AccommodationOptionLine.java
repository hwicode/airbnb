package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
public class AccommodationOptionLine {

    @Id
    @Column(name = "accommodation_option_line_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accomodationOptionLineId;

    private String name;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id")
    private Accommodation accommodation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationOptionLine that = (AccommodationOptionLine) o;
        return accomodationOptionLineId.equals(that.accomodationOptionLineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accomodationOptionLineId);
    }
}

