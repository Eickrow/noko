package com.plugins.noko;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

/**
 * Created by david.yun on 2017/5/7.
 */
public class DateTest {

    @Test
    public void java8() {
        //Java 8中获取当天的日期
        LocalDate today = LocalDate.now();
        System.out.println("Today's Local date : " + today);
        //Java 8中获取当前的年月日
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
        //Java 8中如何获取某个特定的日期
        LocalDate dateOfBirth = LocalDate.of(2017, 05, 07);
        System.out.println("Your Date of birth is : " + dateOfBirth);
        //Java 8中如何检查两个日期是否相等
        if (dateOfBirth.equals(today)) {
            System.out.printf("Today %s and date1 %s are same date %n", today, dateOfBirth);
        }
        //Java 8中如何检查重复事件，比如说生日
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
        //Java 8中获取当前时间
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time);
        //Java 8中增加时间里面的小时数
        LocalTime newTime = time.plusHours(2);
        System.out.println("Time after 2 hours : " + newTime);
        //Java 8中获取1周后的日期
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);
        //Java 8中一年前后的日期
        LocalDate previousYear = today.minus(1, YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        LocalDate nextYear = today.plus(1, YEARS);
        System.out.println("Date after 1 year : " + nextYear);
        //Java 8中使用时钟
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);
        Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);
        //Java 8中如何判断某个日期是在另一个日期的前面还是后面
        LocalDate tomorrow = LocalDate.of(2014, 1, 15);
        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }
        LocalDate yesterday = today.minus(1, DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }
        //Java 8中处理不同的时区
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, ZoneId.of("America/New_York"));
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
        //Java 8中如何表示固定的日期
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
        //Java 8中检查闰年
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println(year + " is not a Leap year");
        }
        //Java 8中两个日期之间包含多少天，多少个月
        LocalDate java8Release = LocalDate.of(2017, Month.FEBRUARY, 07);
        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths());
        //Java 8中带时区偏移量的日期与时间
        LocalDateTime datetime = LocalDateTime.of(2017, Month.JANUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
        //Java 8中如何获取当前时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp);
        //Java 8中使用预定义的格式器来对日期进行解析/格式化
        String dayAfterTommorrow = "20140116";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
        //Java 8中使用自定义的格式器来解析日期
        String goodFriday = "Apr 18 2014";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
            System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable!%n", goodFriday);
            e.printStackTrace();
        }
        //Java 8中对日期进行格式化，转换成字符串
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            String landing = arrivalDate.format(format);
            System.out.printf("Arriving at : %s %n", landing);
        } catch (DateTimeException e) {
            System.out.printf("%s can't be formatted!%n", arrivalDate);
            e.printStackTrace();
        }
    }
}
