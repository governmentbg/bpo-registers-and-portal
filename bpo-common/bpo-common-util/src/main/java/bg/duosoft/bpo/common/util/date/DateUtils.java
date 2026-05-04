package bg.duosoft.bpo.common.util.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Objects;

public class DateUtils {

    public static final String DATE_FORMAT_DOT = "dd.MM.yyyy";
    public static final String DATE_TIME_FORMAT_DOT = "dd.MM.yyyy HH:mm:ss";
    public static final String TIME_FORMAT_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static LocalDateTime toLocalDateTime(Date dateToConvert) {
        return dateToConvert == null ? null : dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert == null ? null : (dateToConvert instanceof java.sql.Date ? ((java.sql.Date) dateToConvert).toLocalDate() : toLocalDateTime(dateToConvert).toLocalDate());
    }

    public static Date toDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static final String formatDateTime(LocalDateTime date) {
        if(Objects.isNull(date)) {
            return null;
        }

        return date.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DOT));
    }

    public static final String formatDate(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }
        
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT_DOT));
    }


    public static LocalDate yearToLocalDate(String year) {
        if (isEmpty(year)) {
            return null;
        }
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        return LocalDate.parse(year, format);
    }

    public static LocalDate toLocalDate(String date) {
        if (isEmpty(date)) {
            return null;
        }
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern(DATE_FORMAT_DOT)
                .toFormatter();

        return LocalDate.parse(date, format);
    }

    public static String toYear(LocalDate localDate) {
        if (Objects.isNull(localDate)){
            return "";
        }
        return  localDate.getYear() + "";
    }

    public static String formatLocalDate(LocalDate localDate) {
        if (Objects.isNull(localDate)){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_DOT);
        return localDate.format(formatter);
    }

    public static LocalDate parseDate(String value) {
        if(value == null) {
            return null;
        }
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT_DOT));
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime parseDateTime(String value) {
        if(value == null) {
            return null;
        }
        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DOT));
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime toTheEndOfTheDay(LocalDateTime dateTime) {
        return dateTime.with(LocalTime.MAX);
    }

    private static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
