package com.iat.utils;

import com.google.common.base.Splitter;

import java.util.Map;

public class StringConverter {

    private static StringConverter instance;

    private StringConverter() {
    }

    public static StringConverter getInstance() {
        if (instance == null)
            instance = new StringConverter();
        return instance;
    }

    public Map<String, String> convertStringToHashMap(String input) {
        return Splitter.on(",").withKeyValueSeparator(":").split(input);
    }
}
