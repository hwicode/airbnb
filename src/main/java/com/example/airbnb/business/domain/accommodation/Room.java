package com.example.airbnb.business.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Room {

    private int bedRooms;
    private int beds;
    private int bathRooms;

    public Room(int beds, int bathRooms, int bedRooms) {
        this.beds = beds;
        this.bathRooms = bathRooms;
        this.bedRooms = bedRooms;
    }

    protected Room() {}

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

