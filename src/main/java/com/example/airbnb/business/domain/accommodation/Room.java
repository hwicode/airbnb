package com.example.airbnb.business.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;

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

    public Room() {
    }
}

