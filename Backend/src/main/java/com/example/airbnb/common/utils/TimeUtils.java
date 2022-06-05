package com.example.airbnb.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtils {

    public static LocalDateTime convert(LocalDate checkOut) {
        return checkOut.atTime(8, 59, 59);
    }
}
