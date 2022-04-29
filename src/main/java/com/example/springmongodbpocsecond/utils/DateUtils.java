package com.example.springmongodbpocsecond.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static final String CUTOFF_TIME_FORMAT = "H:mm";
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("Etc/UTC");
    public static final String DATE_FORMAT_ONLY_DATE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm'Z'";
    public static final String DATE_FORMAT_TIMEZONE_DESIGNATOR = "yyyy-MM-dd'T'HH:mm zzz";
    public static final String DATE_FORMAT_ISO8601_SECONDS = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_IMEZONE_DESIGNATOR_SECONDS = "yyyy-MM-dd'T'HH:mm:ss zzz";
    public static final String DATE_FORMAT_MINIMUM_FIRST_DATE = "dd/MM/yy";

    public static String formatDate(final Date date) {
        return formatDate(date, UTC_TIME_ZONE, DATE_FORMAT_ONLY_DATE);
    }

    public static String formatDateAsISO8601(final Date date) {
        return formatDate(date, UTC_TIME_ZONE, DATE_FORMAT_ISO8601);
    }

    public static String formatGigyaDate(final Date date) {
        return formatDate(date, UTC_TIME_ZONE, DATE_FORMAT_ISO8601_SECONDS);
    }

    public static Date parseGigyaDate(final String date) throws ParseException {
        return parseDate(date, UTC_TIME_ZONE, DATE_FORMAT_ISO8601_SECONDS);
    }

    private static DateFormat getDateFormart(final TimeZone timeZone, final String format) {
        final DateFormat dateFormat = new SimpleDateFormat(format);
        if (timeZone != null) {
            dateFormat.setTimeZone(timeZone);
        }
        return dateFormat;
    }

    public static String formatDate(final Date date, final TimeZone timeZone, final String format) {
        return getDateFormart(timeZone, format).format(date);
    }

    public static Date parseDate(final String date, final TimeZone timeZone, final String format)
            throws ParseException {
        return getDateFormart(timeZone, format).parse(date);
    }

    /**
     *
     * @param start
     * @param end
     * @param type
     *            Calendar type @see java.util.Calendar
     * @return the difference between start and end in millis.
     */
    public static long differenceBetween(Date start, Date end, int type) {
        long diff = end.getTime() - start.getTime();
        switch (type) {
        case Calendar.DATE:
            return TimeUnit.MILLISECONDS.toDays(diff);
        case Calendar.HOUR:
            return TimeUnit.MILLISECONDS.toHours(diff);
        case Calendar.MINUTE:
            return TimeUnit.MILLISECONDS.toMinutes(diff);
        case Calendar.SECOND:
            return TimeUnit.MILLISECONDS.toSeconds(diff);
        default:
            return diff;
        }

    }

    public static TimeZone getDDBBTimeZone() {
        return TimeZone.getTimeZone("Etc/GMT");
    }

    /**
     * Adds or quits time (days, months, weeks, hours, etc) to the given date.
     *
     * @param date
     * @param valueToChange
     * @param type
     *            Calendar type @see java.util.Calendar
     * @return a new date with valueToChange of type added to date.
     */
    public static Date changeDate(Date date, int valueToChange, int type) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(type, valueToChange);

        return cal.getTime();
    }

    public static Date getDatabaseTimestampDate() {
        final Calendar cal = Calendar.getInstance(getDDBBTimeZone());
        return cal.getTime();
    }

}
