package com.pawan.choure.AQR2019.utility;

import com.pawan.choure.AQR2019.model.CentralBook;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class Utility {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(100);
    public static final ConcurrentHashMap<String, CentralBook> centralBookMap= new ConcurrentHashMap<>();

    public static int getUniqueID() {
        return ID_GENERATOR.getAndIncrement();
    }

}
