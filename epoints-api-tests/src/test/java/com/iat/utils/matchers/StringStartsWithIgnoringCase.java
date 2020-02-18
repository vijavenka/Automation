package com.iat.utils.matchers;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class StringStartsWithIgnoringCase extends SubstringMatcher {

    public StringStartsWithIgnoringCase(String substring) {
        super(substring);
    }

    @Factory
    public static Matcher<String> startsWithIgnoringCase(String substring) {
        return new StringStartsWithIgnoringCase(substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.toLowerCase().startsWith(substring.toLowerCase());
    }

    @Override
    protected String relationship() {
        return "starts with ignoring case";
    }
}

