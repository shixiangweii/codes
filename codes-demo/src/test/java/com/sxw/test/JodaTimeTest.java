package com.sxw.test;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-12-27
 * Time: 11:29
 *
 * @author shixiangweii
 */
public class JodaTimeTest {

    /**
     * 没有设置时区导致异常
     * https://blog.csdn.net/u010954806/article/details/79064130
     */
    @Test
    public void testJodaTimeError() {
        // 2.10.3修正了bug
        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1990-04-15").toDate();
        new DateTime("1990-04-15");
    }


    @Test
    public void testJodaTime() {
        Date date = DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalDate("1994-12-26").toDate();
        // 1994/12/26 0:0:0 788371200000
        System.out.println(date.getTime());


        DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalTime("1994-12-26");

        // LocalDateTime is an unmodifiable datetime class representing a
        // datetime without a time zone
        date = DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalDateTime("1994-12-26").toDate();

        // 788371200000
        System.out.println(date.getTime());

        //1940-06-03、1941-03-16、1986-05-04、1987-04-12、1988-04-10、1989-04-16、1990-04-15、1991-04-14
        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1990-04-15").toDate();

        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1990-04-15").toDate();
        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1940-06-03").toDate();

        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1941-03-16").toDate();


        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1986-05-04").toDate();

        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1987-04-12").toDate();

        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1988-04-10").toDate();

        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1989-04-16").toDate();
        DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1989-04-16").toDate();
    }

    @Test
    public void testDateError() {
        DateTime dateTime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1900-01-01");
        for (int i = 0; i < 200 * 365; i++) {
            dateTime = dateTime.plusDays(1);
            String text = dateTime.toString("yyyy-MM-dd");
            System.out.println(text);
            // DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(text).toDate();
            DateTimeFormat.forPattern("yyyy-MM-dd").parseLocalDateTime(text).toDate();
        }
    }

    @Test
    public void testJodaTimeOperation() {
        DateTime now = DateTime.now();
        System.out.println(now.getHourOfDay());
        System.out.println(now.getDayOfWeek());

        System.out.println(DateTimeConstants.FRIDAY);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

        System.out.println(DateTime.parse("2019-4-12 10:46", fmt));

        System.out.println(now.getYear());

        System.out.println(now.getMonthOfYear());
        System.out.println(now.getDayOfMonth());

        System.out.println(System.currentTimeMillis());
    }
}
