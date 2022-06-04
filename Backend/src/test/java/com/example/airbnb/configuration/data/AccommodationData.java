package com.example.airbnb.configuration.data;

import com.example.airbnb.business.core.domain.accommodation.*;
import com.example.airbnb.business.core.domain.member.Gender;
import com.example.airbnb.business.core.domain.member.Member;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccommodationData {
    private Accommodation accommodation;
    private AccommodationOptionLine accommodationOptionLine;
    private AccommodationTag accommodationTag;
    private Member member;
    private City city;
    private Tag tag;
    private List<Image> images;

    public AccommodationData() {
        this.accommodationOptionLine = createAccommodationOptionLine();
        this.images = createImages();
        this.member = createMember();
        this.city = createCity();
        this.accommodation = createAccommodation();
        this.tag = createTag();
        this.accommodationTag = createAccommodationTag();

        accommodation.registImages(images);
        accommodationOptionLine.setAccommodation(accommodation);
    }

    private Accommodation createAccommodation() {
        String mainImage = "https://www.fnnews.com/resource/media/image/2019/07/14/201907141443165141_l.jpg";
        Room room = new Room(3, 3, 2);
        Address address = new Address("서귀포구", "제주도시 올레길", "244-55");
        return Accommodation.builder()
                .name("제주도 민박집")
                .description("제주도 바다가 보이는 숙소")
                .mainImageUrl(mainImage)
                .price(new BigDecimal("390000"))
                .averageRating(4.9)
                .room(room)
                .member(member)
                .accommodationType(AccommodationType.GUEST_HOUSE)
                .address(address)
                .location(new Location(33.4890, 126.4983))
                .maxNumberOfPeople(5)
                .city(city)
                .build();
    }

    private City createCity() {
        return new City("대한민국", "제주도", "https://blog.kakaocdn.net/dn/o1KIw/btqu9mflPY6/rGk1mM3iugV1c6jj9Z3E80/img.jpg");
    }

    private List<Image> createImages() {
        List<Image> images = new ArrayList<>();
        Image image = new Image("https://www.fnnews.com/resource/media/image/2019/07/14/201907141443165141_l.jpg", "zezudo_picture.jpeg");
        images.add(image);
        return images;
    }

    public AccommodationOptionLine createAccommodationOptionLine() {
        return new AccommodationOptionLine("청소비", new BigDecimal("39000"));
    }

    private Member createMember() {
        String profileImage = "https://images.daznservices.com/di/library/Goal_Tanzania/bc/9e/neymar-messi-suarez_113bvr56cm6yt14nf5l21s66de.jpg?t=-1209587743&w=800&h=600";
        return new Member(profileImage, "Jun", "devjun10", Gender.MAN);
    }

    private Tag createTag(){
        return new Tag("제주도");
    }

    private AccommodationTag createAccommodationTag(){
        return new AccommodationTag(tag, accommodation);
    }
}
