package com.example.airbnb.business.core.domain.member;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Wish {

    @Id
    @Column(name = "wish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish wish = (Wish) o;
        return wishId.equals(wish.wishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishId);
    }
}
