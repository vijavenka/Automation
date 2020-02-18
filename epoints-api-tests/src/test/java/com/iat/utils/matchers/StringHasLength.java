package com.iat.utils.matchers;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import static org.hamcrest.core.IsEqual.equalTo;

public class StringHasLength extends FeatureMatcher<String, Integer> {

    public StringHasLength(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "a String with length", "String length");
    }

    @Override
    protected Integer featureValueOf(String actual) {
        return actual.length();
    }

    @Factory
    public static Matcher<String> hasLength(Matcher<? super Integer> lengthMatcher) {
        return new StringHasLength(lengthMatcher);
    }

    @Factory
    public static Matcher<String> hasLength(int length) {
        Matcher<? super Integer> lengthMatcher = equalTo(length);
        return new StringHasLength(lengthMatcher);
    }
}

