package com.iat.storepresentation.Locators;

public class Locator implements ILocator {

    private LocatorType type;
    private String body;

    public Locator(LocatorType type, String body) {
        this.type = type;
        this.body = body;
    }

    public LocatorType get_type() {
        return this.type;
    }

    public String get_body() {
        return this.body;
    }

}
