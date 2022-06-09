package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
public class AccommodationOptionLine {

    @Id
    @Column(name = "accommodation_option_line_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationOptionLineId;

    @NotNull @NotEmpty
    @Column(length = 100)
    private String name;

    @NotNull
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id")
    private Accommodation accommodation;

    public AccommodationOptionLine(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void registAccommodation(Accommodation accommodation) {
        validate(accommodation);
        this.accommodation = accommodation;
    }

    private void validate(Accommodation accommodation) {
        if (accommodation == null) {
            throw new IllegalArgumentException("숙소 정보를 입력해주세요.");
        }
    }

    protected AccommodationOptionLine() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationOptionLine that = (AccommodationOptionLine) o;
        return accommodationOptionLineId.equals(that.accommodationOptionLineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accommodationOptionLineId);
    }
}

