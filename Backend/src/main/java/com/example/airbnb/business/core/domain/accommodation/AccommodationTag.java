package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
public class AccommodationTag {

    @Id
    @Column(name = "accommodation_tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public AccommodationTag(Tag tag, Accommodation accommodation) {
        this.tag = tag;
        this.accommodation = accommodation;
    }

    public AccommodationTag() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationTag that = (AccommodationTag) o;
        return accommodationTagId.equals(that.accommodationTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accommodationTagId);
    }
}
