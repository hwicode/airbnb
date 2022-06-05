package com.example.airbnb.business.core.domain.accommodation;


import com.example.airbnb.business.core.domain.member.Member;
import lombok.Builder;
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

    private String mainImageUrl;

    private Double averageRating;

    private Integer commentCount;

    @Embedded
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Embedded
    private Address address;

    @Embedded
    private Location location;

    private Integer maxNumberOfPeople;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.PERSIST)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private City city;

    // 숙소를 등록할 때 commentCount(댓글 갯수)는 0으로 처리
    @Builder
    public Accommodation(String name, String description, String mainImageUrl, BigDecimal price,
                         Double averageRating, Room room, AccommodationType accommodationType,
                         Address address, Location location, List<Image> images,
                         Integer maxNumberOfPeople) {
        this.name = name;
        this.description = description;
        this.mainImageUrl = mainImageUrl;
        this.commentCount = 0;
        this.price = price;
        this.averageRating = averageRating;
        this.room = room;
        this.accommodationType = accommodationType;
        this.address = address;
        this.location = location;
        this.images = images;
        this.maxNumberOfPeople = maxNumberOfPeople;
        registerImages(images);
    }

    protected Accommodation() {
    }

    private void registerImages(List<Image> images) {
        for (Image image : images) {
            if (image != null) {
                image.registerAccommodation(this);
            }
        }
    }

    public void addInformation(Member member, City city) {
        this.member = member;
        this.city = city;
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
