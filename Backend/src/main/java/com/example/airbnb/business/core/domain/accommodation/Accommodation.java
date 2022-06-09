package com.example.airbnb.business.core.domain.accommodation;


import com.example.airbnb.business.core.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    @Column(length = 2000)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    @NotEmpty
    @Column(length = 1000)
    private String mainImageUrl;

    private Double averageRating;

    private Integer commentCount;

    @Embedded
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;

    @Embedded
    private Address address;

    @Embedded
    private Location location;

    @NotNull
    private Integer maxNumberOfPeople;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.PERSIST)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private City city;

    @Builder
    public Accommodation(String name, String description, String mainImageUrl, BigDecimal price,
                         Room room, AccommodationType accommodationType, Address address, Integer maxNumberOfPeople) {
        this.name = name;
        this.description = description;
        this.mainImageUrl = mainImageUrl;
        this.commentCount = initCommentCount();
        this.price = price;
        this.averageRating = initAverageRating();
        this.room = room;
        this.accommodationType = accommodationType;
        this.address = address;
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    private Integer initCommentCount() {
        return 0;
    }

    private Double initAverageRating() {
        return 0.0;
    }

    protected Accommodation() {
    }

    public void registerImages(List<Image> images) {
        for (Image image : images) {
            if (image != null) {
                image.registerAccommodation(this);
            }
        }
    }

    public void registImage(Image image) {
        if (image != null) {
            this.images.add(image);
        }
    }

    public void addInformation(Member member, City city) {
        validate(member, city);
        this.member = member;
        this.city = city;
    }

    private void validate(Member member, City city) {
        if (member == null || city == null) {
            throw new IllegalStateException("회원, 도시 정보를 모두 입력해주세요.");
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

    public void update(Double latitude, Double longitude) {
        this.location = new Location(longitude, latitude);
    }
}
