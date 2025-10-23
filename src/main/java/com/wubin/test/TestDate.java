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
//        System.out.println(DateUtil.parse("2024-04-04"));
//        System.out.println(DateUtil.parse("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.parseCST("Thu Sep 04 09:17:46 CST 2025"));
//        System.out.println(DateUtil.parseUTC("2024-04-04T08:00:00.786Z"));
//        System.out.println(DateUtil.parseDate("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.parseDateTime("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.parseTimeToday("08:00:00"));
//        System.out.println(DateUtil.today());
//        System.out.println(DateUtil.beginOfDay(new Date()));
        System.out.println(DateUtil.endOfDay(new Date()));
//        System.out.println(DateUtil.hour(DateUtil.parse("2023-01-31 14:00:00"), true));
//        System.out.println(DateUtil.thisHour(true));

//        System.out.println(DateUtil.year(new Date()));

//        Date date1 = DateUtil.parse("2024-03-28 08:01:01");
//        Date date2 = DateUtil.parse("2024-03-28 08:31:02");
//        System.out.println(DateUtil.between(date1, date2, DateUnit.MINUTE));

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
    }

}
