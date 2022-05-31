package com.example.airbnb.business.core.domain.accommodation;


import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.domain.reservation.Time;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Accommodation {

    @Id
    @Column(name = "accommodation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;

    private String name;

    private String description;

    private BigDecimal price;

    private double averageRating;

    @Embedded
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.PERSIST)
    private List<Reservation> reservations = new ArrayList<>();

    @Embedded
    private Location location;

    private int maxNumberOfPeople;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.PERSIST)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private City city;

    public Accommodation(String name, String description, BigDecimal price,
                         double averageRating, Room room, Member member, AccommodationType accommodationType,
                         Address address, List<Reservation> reservations, Location location,
                         int maxNumberOfPeople, City city) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.averageRating = averageRating;
        this.room = room;
        this.member = member;
        this.accommodationType = accommodationType;
        this.address = address;
        this.reservations = reservations;
        this.location = location;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.city = city;
    }

    protected Accommodation() {
    }

    public void registImages(List<Image> images) {
        for (Image image : images) {
            if (image != null) {
                image.registAccommodation(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accommodation that = (Accommodation) o;
        return Objects.equals(accommodationId, that.accommodationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accommodationId);
    }
}
