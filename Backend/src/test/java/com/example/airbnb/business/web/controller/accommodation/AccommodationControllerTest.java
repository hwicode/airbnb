package com.example.airbnb.business.web.controller.accommodation;

import com.example.airbnb.business.core.domain.accommodation.*;
import com.example.airbnb.business.core.domain.member.Gender;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.accommodation.CityRepository;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.business.web.service.accommodation.AccommodationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CityRepository cityRepository;

    private Accommodation accommodation;
    private AccommodationOptionLine accommodationOptionLine;
    private Member member;
    private City city;
    private List<Image> images;

    @BeforeEach
    void setUp() {
        this.accommodation = createAccommodation();
        this.accommodationOptionLine = createAccommodationOptionLine();
        this.images = createImages();
        this.member = createMember();
        this.city = createCity();
        this.member = memberRepository.save(member);
        this.city = cityRepository.save(city);
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
        return new City("대한민국", "서울", "https://blog.kakaocdn.net/dn/o1KIw/btqu9mflPY6/rGk1mM3iugV1c6jj9Z3E80/img.jpg");
    }

    private List<Image> createImages() {
        List<Image> images = new ArrayList<>();
        Image image = new Image("https://www.fnnews.com/resource/media/image/2019/07/14/201907141443165141_l.jpg", "zezudo_picture.jpeg");
        images.add(image);
        return images;
    }

    public AccommodationOptionLine createAccommodationOptionLine(){
        return new AccommodationOptionLine("청소비", new BigDecimal("39000"));
    }

    private Member createMember() {
        String profileImage = "https://images.daznservices.com/di/library/Goal_Tanzania/bc/9e/neymar-messi-suarez_113bvr56cm6yt14nf5l21s66de.jpg?t=-1209587743&w=800&h=600";
        return new Member(profileImage, "Jun", "devjun10", Gender.MAN);
    }

    @Test
    @DisplayName("Accomodation을 id로 조회했을때, 해당 엔티티가 존재하면 id에 해당하는 엔티티가 반환된다.")
    void findAccommodationById() throws Exception {
        Long id = 1L;
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/accommodations/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("accommodation-get", pathParameters(parameterWithName("id").description("AccommodationId")),
                        responseFields(
                                fieldWithPath("accommodationId").description("accommodationId"),
                                fieldWithPath("hostId").description("hostId"),
                                fieldWithPath("description").description("description"),
                                fieldWithPath("hostName").description("hostName"),
                                fieldWithPath("hostProfileImage").description("profileImage").type(String.class),
                                fieldWithPath("name").description("name"),
                                fieldWithPath("oneDayPerPrice").description("oneDayPerPrice"),
                                fieldWithPath("address").description("address"),
                                fieldWithPath("accommodationType").description("accommodationType"),
                                fieldWithPath("averageRating").description("averageRating"),
                                fieldWithPath("images").description("images"),
                                fieldWithPath("comments").description("comments"),
                                fieldWithPath("amenityCategories").description("amenityCategories"),
                                fieldWithPath("accommodationOptionLines.[].name").description("accommodationOptionLines-Name"),
                                fieldWithPath("accommodationOptionLines.[].price").description("accommodationOptionLines-Name"),
                                fieldWithPath("commentSize").description("commentSize"),
                                fieldWithPath("maxNumberOfPeople").description("maxNumberOfPeople"),
                                fieldWithPath("beds").description("beds"),
                                fieldWithPath("bedRooms").description("bedRooms"),
                                fieldWithPath("bathRooms").description("bathRooms")
                        )
                ));
    }


}
