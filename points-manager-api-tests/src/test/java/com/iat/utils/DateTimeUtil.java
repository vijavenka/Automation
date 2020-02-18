package com.iat.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

/**
 * Date & time utility
 */
public class DateTimeUtil {

    public static Date getStartOfDay(Date date) {
        return adjustTimeOfTheDay(date, 0, 0, 0, 0);
    }

    public static Date getEndOfDay(Date date) {
        return adjustTimeOfTheDay(date, 23, 59, 59, 0);
    }

    public static Date adjustTimeOfTheDay(Date date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = getCalendar(date);
        if (hour > -1 && hour < 24) calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (minute > -1 && minute < 60) calendar.set(Calendar.MINUTE, minute);
        if (second > -1 && second < 60) calendar.set(Calendar.SECOND, second);
        if (millisecond > -1 && millisecond < 1000) calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date addUtcOffset(Date date) {
        return adjustDateBySeconds(date, -getLocalFromUtcOffset(date));
    }

    private static int getLocalFromUtcOffset(Date date) {
        return ZonedDateTime.of(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), ZoneId.systemDefault()).getOffset().getTotalSeconds();
    }

    /**
     * Adjusting given time to the future or the past
     *
     * @param date    date which is to be adjusted
     * @param seconds
     * @return
     */
    public static Date adjustDateBySeconds(Date date, int seconds) {
        return adjustDate(date, Calendar.SECOND, seconds);
    }

    public static Date adjustDateByHours(Date date, int hours) {
        return adjustDate(date, Calendar.HOUR, hours);
    }

    public static Date adjustDateByDays(Date date, int days) {
        return adjustDate(date, Calendar.DATE, days);
    }

    public static Date adjustDateByYears(Date date, int years) {
        return adjustDate(date, Calendar.YEAR, years);
    }

    public static Date adjustDateByMonths(Date date, int months) {
        return adjustDate(date, Calendar.MONTH, months);
    }

    private static Date adjustDate(Date date, int calendarOption, int value) {
        Calendar calendar = getCalendar(date);
        calendar.add(calendarOption, value);
        return calendar.getTime();
    }

    public static int getDateDay(Date date) {
        return getDatePart(date, Calendar.DAY_OF_MONTH);
    }

    public static int getDateMonth(Date date) {
        return getDatePart(date, Calendar.MONTH) + 1;
    }

    public static int getDateYear(Date date) {
        return getDatePart(date, Calendar.YEAR);
    }

    private static int getDatePart(Date date, int calendarOption) {
        return getCalendar(date).get(calendarOption);
    }

    public static Date convertToDate(String dateString, String dateFormat, boolean originalWasUtc) {
        DateFormat df = new SimpleDateFormat(dateFormat);

        try {
            Date date = df.parse(dateString);
            if (originalWasUtc) date = adjustDateBySeconds(date, getLocalFromUtcOffset(date));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date convertToDate(String dateString, String dateFormat) {
        return convertToDate(dateString, dateFormat, false);
    }

    public static String convertToString(Date date, String dateFormat, boolean shouldBeUtc) {
        DateFormat df = new SimpleDateFormat(dateFormat);

        try {
            if (shouldBeUtc) date = adjustDateBySeconds(date, -getLocalFromUtcOffset(date));
            return df.format(date);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToString(Date date, String dateFormat) {
        return convertToString(date, dateFormat, false);
    }

    public static long diffBetweenDates(Date startDate, Date endDate, int value) {
        if (endDate.before(startDate))
            return diffBetweenDates(endDate, startDate, value);

        long duration = endDate.getTime() - startDate.getTime();

        switch (value) {
            case Calendar.SECOND:
                return TimeUnit.MILLISECONDS.toSeconds(duration);
            case Calendar.MINUTE:
                return TimeUnit.MILLISECONDS.toMinutes(duration);
            case Calendar.HOUR:
                return TimeUnit.MILLISECONDS.toHours(duration);
        }

        assertThat("Invalid variable used!", value, isOneOf(Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR));
        return 0L;
    }

    public interface Format {
        String ddMMyyyy = "dd/MM/yyyy";
        String dd_MM_yyyy = "dd-MM-yyyy";
        String ddMMyy = "dd/MM/yy";
        String yyyyMMddHHmmss = "yyyyMMddHHmmss";
        String ddMMyyyy_HHmmss = "dd/MM/yyyy HH:mm:ss";
        String ddMMyy_HHmmss = "dd/MM/yy HH:mm:ss";
        String ddMMMyyyy = "dd-MMM-yyyy";
        String ddMMMyyyy_2 = "dd MMM yyyy";
        String yyyyMMdd = "yyyy-MM-dd";
        String yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
        String yyyyMMddTHHmmssXXX = "yyyy-MM-dd'T'HH:mm:ssXXX";
        String yyyyMMddTHHmmssZ = "yyyy-MM-dd'T'HH:mm:ssZ";
        String ddMMyyyy_HHmm_XXX = "dd-MM-yyyy HH:mm XXX";
        String MMyyyy = "MM-yyyy";
        String ddMMyyyy_HHmmss_2 = "dd.MM.yyyy HH:mm:ss";
    }
}
