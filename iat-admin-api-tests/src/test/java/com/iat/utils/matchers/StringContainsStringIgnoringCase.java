package com.iat.utils.matchers;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class StringContainsStringIgnoringCase extends SubstringMatcher {

    public StringContainsStringIgnoringCase(String substring) {
        super(substring);
    }

    @Factory
    public static Matcher<String> containsStringIgnoringCase(String substring) {
        return new StringContainsStringIgnoringCase(substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.toLowerCase().contains(substring.toLowerCase());
    }

    @Override
    protected String relationship() {
        return "contains ignoring case";
    }
}

