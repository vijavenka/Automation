package com.iat.utils;

import com.google.common.base.Splitter;

import java.util.Map;

/**
 * Created by lpanusz on 2016-01-27.
 */
public class StringConverter {

    private static StringConverter instance = new StringConverter();

    private StringConverter() {
    }

    public static StringConverter getInstance() {
        return instance;
    }

    public Map<String, String> convertStringToHashMap(String input, String splitter, String separator) {
        return Splitter.on(splitter).withKeyValueSeparator(separator).split(input);
    }

    public String[] splitStringToArray(String input) {
        return input.split(",");
    }
}
