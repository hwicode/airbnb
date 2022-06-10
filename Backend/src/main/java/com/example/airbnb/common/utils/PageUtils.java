package com.example.airbnb.common.utils;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtils {

    public static final int NEXT = 1;
    public static final int PAGE_SIZE = 10;

    public static boolean getHasNext(Pageable pageable, List<?> result) {
        boolean hasNext = false;

        if (result.size() > pageable.getPageSize()) {
            result.remove(pageable.getPageSize());
            hasNext = true;
        }
        return hasNext;
    }
}
