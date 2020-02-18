package com.iat.utils.matchers;

import org.hamcrest.Matcher;

public class CustomMatchers {

    public static Matcher<String> containsStringIgnoringCase(String substring) {
        return com.iat.utils.matchers.StringContainsStringIgnoringCase.containsStringIgnoringCase(substring);
    }

    public static Matcher<String> hasLength(Matcher<? super Integer> lengthMatcher) {
        return com.iat.utils.matchers.StringHasLength.hasLength(lengthMatcher);
    }

    public static Matcher<String> hasLength(int lengthMatcher) {
        return com.iat.utils.matchers.StringHasLength.hasLength(lengthMatcher);
    }

    public static <T> Matcher<Iterable<T>> hasFirstItem(Matcher<T> item) {
        return com.iat.utils.matchers.HasItemAtIndex.hasFirstItem(item);
    }

    public static <T> Matcher<Iterable<T>> hasFirstItem(T value) {
        return com.iat.utils.matchers.HasItemAtIndex.hasFirstItem(value);
    }

    public static <T> Matcher<Iterable<T>> hasLastItem(Matcher<T> item) {
        return com.iat.utils.matchers.HasItemAtIndex.hasLastItem(item);
    }

    public static <T> Matcher<Iterable<T>> hasLastItem(T value) {
        return com.iat.utils.matchers.HasItemAtIndex.hasLastItem(value);
    }

    public static <T> Matcher<Iterable<T>> hasItemAtIndex(int index, Matcher<T> item) {
        return com.iat.utils.matchers.HasItemAtIndex.hasItemAtIndex(index, item);
    }

    public static <T> Matcher<Iterable<T>> hasItemAtIndex(int index, T value) {
        return com.iat.utils.matchers.HasItemAtIndex.hasItemAtIndex(index, value);
    }
}
