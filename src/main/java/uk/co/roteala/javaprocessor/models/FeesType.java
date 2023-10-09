package uk.co.roteala.javaprocessor.models;

import java.util.HashMap;
import java.util.Map;

public enum FeesType {
    BILLING(1),
    ITEMS(2);

    private final int intValue;

    private static final Map<Integer, FeesType> VALUES = new HashMap<>();

    // static initialiser cache.
    static {
        for (FeesType type : values()) {
            VALUES.put(type.intValue, type);
        }
    }


    FeesType(int value) {
        intValue = value;
    }


    public int getValue() {
        return intValue;
    }


    public static FeesType getFeesType(int value) {
        return VALUES.get(value);
    }
}
