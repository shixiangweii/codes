package com.sxw.test.localdate;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author shixiangweii
 * @date 2019/9/14 23: 27
 * https: //www.jianshu.com/p/93c7bcbc9397
 * https: //zhidao.baidu.com/question/131769650.html
 * https: //www.jianshu.com/p/c403b2906da5
 * http: //blog.sina.com.cn/s/blog_9dcb9ce70102x9nq.html
 * https: //www.jianshu.com/p/7661b33d2e5c
 * https: //zhidao.baidu.com/question/2120725840023563827.html
 * <p>
 * UTC Universal Time Coordinated 协调世界时
 * GMT Greenwich Mean Time 格林尼治标准时间
 * <p>
 * UNIX时间又称POSIX时间/新纪元时间（Epoch Time）：从协调世界时(UTC)1970年1月1日0时0分0秒起到现在的总秒数，
 * 不包括闰秒。正值表示1970以後，负值则表示1970年以前
 * <p>
 * GMT可以理解成，老版本的基准时间，0号时区
 * UTC可以理解成，新版的基准时间，0号时区
 * +8: 00 东8区 北京时间
 * T是代表后面跟着“时间”。Z代表0时区,UTC统一时间
 */
public class LocalDateTest {

    @Test
    public void testTimeZoneID() {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        String s[] = c.getTimeZone().getAvailableIDs();
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }

    @Test
    public void test() {
        // 直接获取当前时刻
        Instant instant = Instant.now();
        // UTC 0时区
        System.out.println("instant: " + instant);

        // 用Asia/Shanghai格式化，+8:00
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("zonedDateTime by Asia/Shanghai: " + zonedDateTime);

        // UTC 0时区格式化，+0
        zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        System.out.println("zonedDateTime by UTC: " + zonedDateTime);

        // currentTimeMillis是UTC 1970 00:00:00 时间到现在的毫秒数
        Instant bySystemCurTimeMillis = Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println("bySystemCurTimeMillis: " + bySystemCurTimeMillis);

        //1.3 Date也表示时刻，所以instant 和date可以通过Epoch Time 纪元时转换
        Instant byDate = Instant.ofEpochMilli(new Date().getTime());
        System.out.println("byDate: " + byDate);

        Date date = new Date(byDate.toEpochMilli());
        System.out.println("date: " + date);

        //2.1 直接获取 UTC
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);

        // 2.3 同时LocalDateTime有很多方法：例如单独获取年\月\日\小时\分钟\周几
        int year = localDateTime.getYear();
        int dayOfYear = localDateTime.getDayOfYear();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println("year: " + year);
        System.out.println("dayOfYear: " + dayOfYear);
        System.out.println("dayOfWeek: " + dayOfWeek);

        //2.2 指定年月日获取 UTC
        LocalDateTime localDateBySet = LocalDateTime.of(2019, 2, 15, 20, 20);
        System.out.println("localDateBySet: " + localDateBySet);
    }

    @Test
    public void testZoneCalc() {
        ZoneOffset zoneOffset = (ZoneOffset) ZoneId.of("+01:00");
        System.out.println(zoneOffset);

        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.of("+01:00"));
        System.out.println(instant);

        // 转换为Instant不带时区信息
        instant = ZonedDateTime.now().toInstant();
        System.out.println(instant);
    }

    /**
     * TemporalAdjusters.lastDayOfMonth();
     * TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY);
     * TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY);
     * TemporalAdjusters.next(DayOfWeek.TUESDAY);
     */
    @Test
    public void testDateTransfer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String patternString = formatter.format(ZonedDateTime.now());
        System.out.println(patternString);
        // "Local"，"本地"，当前这台计算机
        LocalDateTime localDateTime = LocalDateTime.parse("1990-04-15 00:00:00", formatter);
        System.out.println(localDateTime);

        // 日期计算
        localDateTime = LocalDateTime.now().minusDays(10).plusDays(9).plusWeeks(2);
        System.out.println(localDateTime);

        //先plusWeek加一周,再调用with指定时间
        LocalDateTime nextTue = LocalDateTime.now().plusWeeks(1).with(ChronoField.DAY_OF_WEEK, 2);
        System.out.println(nextTue);
        //使用 TemporalAdjusters 工具类的next方法直接指定时间
        nextTue = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(nextTue);

        // https://blog.csdn.net/qq_26974471/article/details/89151345
        // 年月日
        // 相差，8年，11个月，0天
        Period period = Period.between(LocalDate.of(2019, 9, 1), LocalDate.of(2028, 8, 1));
        // 8，所以这里差了8
        System.out.println(period.getYears());
        // 11，不会加上年，所以差了11个月
        System.out.println(period.getMonths());
        // 0，不会算上年，月，所以差了0天
        System.out.println(period.getDays());

        // 时分秒
        Duration duration = Duration.between(LocalTime.of(10, 10), LocalTime.now());
        System.out.println(duration.getSeconds());

    }
}
