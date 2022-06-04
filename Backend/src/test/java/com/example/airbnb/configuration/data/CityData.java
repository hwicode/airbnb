package com.example.airbnb.configuration.data;

import com.example.airbnb.business.core.domain.accommodation.City;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.CityTypeException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CityData {

    private List<City> cities;

    public City findCityByName(String name){
        return cities.stream()
                .filter(x->x.getName().equals(name))
                .findAny()
                .orElseThrow(()->new BusinessException(CityTypeException.CITY_NOT_FOUND));
    }

    public CityData() {
        this.cities = createCities();
    }

    private List<City> createCities() {
        String seoulImage = "https://cdn.britannica.com/57/75757-050-122EC2ED/Changgyong-Palace-background-Seoul.jpg?w=690&h=388&c=crop";
        String gwangjuImage = "https://www.google.com/maps/vt/data=SWXkg7Ot01cAIlZ7wGO094rtNpR0oeqAWid-cOuNsA-HyNm7XmO8MPparX0zrQcKPbBwlTolGWBJw81vOqsdDuvB26XqEJJDE-_vuy1N85Q1-weDVH1-ij9JYQy2S6M8pURPlDM3Ht2P7-6kvyx1TiUs7aZ95ZG2voMPo6U_Vu1Z-1_j3q491vJfzsa7bLdWh9CBHUMxGD7i6V9ziHCd-7cU4-fA8tvr_Eomwlc_gH4yyTRLdBYnHYd31W7x";
        String uijeongbuImage = "https://www.google.com/maps/vt/data=SWXkg7Ot01cAIlZ7wGO094rtNpR0oeqAWid-cOuNsA-HyNm7XmO8MPparX0zrQcKPbBwlTolGWBJw81vOqsdDuvB26XqEJJDE-_vuy1N85Q1-weDVH1-ij9JYQy2S6M8pURPlDM3Ht2P7-6kvyx1TiUs7aZ95ZG2voMPo6U_Vu1Z-1_j3q491vJfzsa7bLdWh9CBHUMxGD7i6V9ziHCd-7cU4-fA8tvr_Eomwlc_gH4yyTRLdBYnHYd31W7x";
        String suwonImage = "https://www.google.com/maps/vt/data=b8XA8v_xDy1Beq6m8TGQ3-euVLTU2I_r7bflPJ0qELfgwgzt3kALkW0c6-2vKed_KAbxffoKV8w_Vc33hm3NyN0HMY27S9xOPwKPLEYZaZviNkZfZlEdTTEl0gMCgRvKdQPaaSVy-zgWN3YCtE59lIXJI2GiK6EOxRnqSwf2Rw9hN5yIbrgwBLcy4X9u7PgpwoXn3cC1GAuHJ1S77nz1Q4WGAiwdzcPSkYNgtZG1ettuF1MmfbB-NinTQmRZ0w";
        String daeguImage = "https://www.google.com/maps/vt/data=O2tUMV-bIttB1c-RP2gkyjEc_dw1qjWYvrFRLldx6EOUR9arR81AmyZNxBTmFScst1tQt9YtMojzsF8zXJ7EK6n6KoiUaRTH3LEAGiLWcuFkNEyaGkFR_FKFv8oEpFAh3CMfSJhqLW5pBnYjFTyw6pdCKgd3fpHrO8m0V3wACZWdWEzG3isnG6ew2S9FFjEVGycwjUrP6Pc9WYRcpuas1d0X_C8ZjetpuX7nHu4-Niv7o0IYQlkzsLU9QDtd7Q";
        String ulsanImage = "https://www.google.com/maps/vt/data=aZ8pimlO16nTNVxunpVd0x7uM0tXLArew8gFtiCIY0LaUo_9kwhcDAq8YeGshkORsF4G14ToPACD2A-jh4L7L9o-bFPyTNYNYLbjuug3RL5IdmfEKFaujtgmMGCjytz-HQKFe1se80g-W3K7Elj6rwExyL8KI9IFC4ZYJCP_o6vcar94ShoMt7jFisvh0ppII7vdApf1otad0GBAAQm-u8QUJBNgGOd8rPPWvKptUP9Svn7pbcS5yVb7tiTtcg";

        List<City> cities = new ArrayList<>();

        City seoul = new City("대한민국", "서울", seoulImage);
        City gwangju = new City("대한민국", "광주", gwangjuImage);
        City uijeongbu = new City("대한민국", "의정부", uijeongbuImage);
        City suwon = new City("대한민국", "수원", suwonImage);
        City daegu = new City("대한민국", "서울", daeguImage);
        City ulsan = new City("대한민국", "서울", ulsanImage);

        cities.add(seoul);
        cities.add(gwangju);
        cities.add(uijeongbu);
        cities.add(suwon);
        cities.add(daegu);
        cities.add(ulsan);
        return cities;
    }

}
