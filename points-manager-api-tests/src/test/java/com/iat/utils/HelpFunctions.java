package com.iat.utils;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class HelpFunctions {

    public String returnNeededJsonFieldValue(String jsonString, String fieldName) {
        String fieldValue = "";
        if (!jsonString.substring(0, 0).equals("["))
            jsonString = "[" + jsonString + "]";
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            fieldValue = explrObject.get(fieldName).toString();
        }
        return fieldValue;
    }

    public int returnRandomValue(int range) {
        return new Random().nextInt(range);
    }

    public int returnDayOfYearFromTimestamp(Long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public long returnEpochOfCurrentDay() {
        return new Date().getTime();
    }

    public String changeDateFormat(String date, String initialDateFormat, String wantedDateFormat) throws ParseException {
        SimpleDateFormat sdf;

        switch (date) {
            case "current":
                sdf = new SimpleDateFormat(wantedDateFormat);
                return sdf.format(new Date());
            case "yesterday":
                sdf = new SimpleDateFormat(wantedDateFormat);
                return sdf.format(new DateTime().minusDays(1).toDate());
            default:
                sdf = new SimpleDateFormat(initialDateFormat, Locale.ENGLISH);
                sdf.applyPattern(wantedDateFormat);
                return sdf.format(new Date(sdf.parse(date).getTime()));
        }
    }

}