package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Room {

    private Integer bedRooms;
    private Integer beds;
    private Integer bathRooms;

    public Room(Integer beds, Integer bathRooms, Integer bedRooms) {
        validate(beds, bathRooms, bedRooms);
        this.beds = beds;
        this.bathRooms = bathRooms;
        this.bedRooms = bedRooms;
    }

    private void validate(Integer beds, Integer bathRooms, Integer bedRooms) {
        if (beds == null || bathRooms == null || bedRooms == null) {
            throw new IllegalStateException("숙소 정보를 정확히 입력해주세요.");
        }
    }

    protected Room() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return bedRooms == room.bedRooms && beds == room.beds && bathRooms == room.bathRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bedRooms, beds, bathRooms);
    }
}

