package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.*;

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
}
