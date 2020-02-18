package com.iat.stepdefs.utils

import geb.Browser
import org.openqa.selenium.JavascriptExecutor

import java.text.ParseException
import java.text.SimpleDateFormat

def int returnRandomValue(int range) {
    Random rand = new Random();
    int random = rand.nextInt(range);
    return random;
}

def returnCurrentEpochTime(){
    Date dateTime = new Date();
    return dateTime.getTime()
}

def void clearCookiesAndLocalStorage() {
    //clear local storage
    def browser = new Browser()
    ((JavascriptExecutor) browser.getDriver()).executeScript("window.localStorage.clear();")

    browser.clearCookies()
    browser.getDriver().navigate().refresh()
    Thread.sleep(1000)
}

def void refreshPage() {
    def browser = new Browser()
    browser.getDriver().navigate().refresh()
    Thread.sleep(1000)
}

public Long parseDateFromStringToMiliseconds(String date, String format) {

    SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
    Date dateTime = new Date();
    try {
        dateTime = formatter.parse(date);

    } catch (ParseException e) {
        e.printStackTrace();
    }
    return dateTime.getTime();
}

def changeDateFormat(String date, String initialDateFormat, String wantedDateFormat) {
    final String dateUIFormat = initialDateFormat;
    final String dateDBFormat = wantedDateFormat;
    String DBdateAfterFormatting = null;

    SimpleDateFormat sdf = new SimpleDateFormat(dateUIFormat, Locale.ENGLISH);
    Date d = sdf.parse(date);
    d = new Date(d.getTime());
    sdf.applyPattern(dateDBFormat);
    DBdateAfterFormatting = sdf.format(d);

    return DBdateAfterFormatting;
}

def returnRandomString(int len) {
    String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    Random rnd = new Random()
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++)
        sb.append(AB.charAt(rnd.nextInt(AB.length())));
    return sb.toString();
}
