package com.wubin.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.*;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestDate {

    public static void main(String[] args) {
//        try {
//            String[] patterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
//            System.out.println(DateUtils.parseDate("2020-8-8", patterns));
//            System.out.println(DateUtils.parseDate("2020-08-08 08:08:08", patterns));
//            System.out.println(DateUtils.parseDateStrictly("2020-8-8", patterns));
//            System.out.println(DateUtils.parseDateStrictly("2020-08-08 08:08:08", patterns));
//            System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        } catch (ParseException e) {
//            System.out.println(ExceptionUtils.getStackTrace(e));
//            System.out.println(ExceptionUtil.stacktraceToString(e));
//        }

//        System.out.println(DateUtil.format(new Date(), "yyyyMMddHHmmssSSS"));
//        System.out.println(DateUtil.formatDate(new Date()));
//        System.out.println(DateUtil.parse("2024-04-04"));
//        System.out.println(DateUtil.parse("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.parseCST("Thu Sep 04 09:17:46 CST 2025"));
//        System.out.println(DateUtil.parseUTC("2024-04-04T08:00:00.786Z"));
//        System.out.println(DateUtil.parseDate("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.parseDateTime("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.parseTimeToday("08:00:00"));
//        System.out.println(DateUtil.today());
//        System.out.println(DateUtil.beginOfHour(new Date()));
//        System.out.println(DateUtil.endOfHour(new Date()));
//        System.out.println(DateUtil.hour(DateUtil.parse("2023-01-31 14:00:00"), true));
//        System.out.println(DateUtil.thisHour(true));
//        System.out.println(DateUtil.age(DateUtil.parse("1993-02-22"), new Date()));

//        System.out.println(DateUtil.year(new Date()));

//        System.out.println(DateUtil.offsetDay(new Date(), 1));
//        System.out.println(new Date().compareTo(DateUtil.parseDate("2026-04-01 00:00:00")) < 0);
//        System.out.println(DateUtil.offsetDay(DateUtil.endOfMonth(new Date()), 9));

//        Date date1 = DateUtil.parse("2024-03-28 08:00:00");
//        Date date2 = DateUtil.parse("2024-03-28 08:59:59");
//        System.out.println(DateUtil.between(date1, date2, DateUnit.HOUR));

//        Date date1 = DateUtil.parse("2024-03-01 00:00:00");
//        Date date2 = DateUtil.parse("2024-12-01 00:00:00");
//        List<DateTime> rangeToList = DateUtil.rangeToList(date1, date2, DateField.MONTH);
//        for (DateTime dateTime : rangeToList) {
//            System.out.println(dateTime.toDateStr());
//            System.out.println(dateTime.toString("MM-dd"));
//            System.out.println(dateTime.toString("M"));
//        }

//        Date date1 = DateUtil.parse("2023-01-31 00:00:00");
//        Date date2 = DateUtil.parse("2024-01-31 00:00:00");
//        List<DateTime> rangeToList = CollUtil.newArrayList((Iterable<DateTime>) new DateRange(date1, date2, DateField.MONTH, 2, true, false));
//        for (DateTime dateTime : rangeToList) {
//            System.out.println(dateTime);
//        }

//        Date date1 = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date1);
//        cal.add(Calendar.HOUR_OF_DAY, 5);
//        Date date2 = cal.getTime();
//        System.out.println(DateUtil.format(date1, "yyyyMMdd HHmmssSSS"));
//        System.out.println(DateUtil.format(date2, "yyyyMMdd HHmmssSSS"));

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            System.out.println(simpleDateFormat.parse("2026-09-30T05:57:37"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 偏移时间
     *
     * @param date   时间
     * @param field  偏移单位
     * @param offset 偏移量，负数代表往前，正数代表往后
     * @return
     */
    public static Date timeOffSet(Date date, int field, int offset) {
        Calendar cal = Calendar.getInstance(); // 当前时间
        cal.setTime(date); // 设置为参数的时间
        cal.add(field, offset); // 偏移
        return cal.getTime(); // 偏移后的时间
    }

}
