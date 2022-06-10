package com.example.airbnb.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtils {

    public static LocalDateTime convertCheckout(LocalDate checkOut) {
        return checkOut.atTime(8, 59, 59);
    }

    public static LocalDateTime convertCheckin(LocalDate checkOut) {
        return checkOut.atTime(9, 0, 1);
    }
}
