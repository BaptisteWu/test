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

//        System.out.println(DateUtil.today());
//        System.out.println(DateUtil.parse("2024-04-04"));
//        System.out.println(DateUtil.parse("2024-04-04 08:00:00"));
//        System.out.println(DateUtil.hour(DateUtil.parse("2023-01-31 14:00:00"), true));
//        System.out.println(DateUtil.thisHour(true));

//        Date date1 = DateUtil.parse("2024-03-29 08:00:02");
//        Date date2 = DateUtil.parse("2024-03-29 08:01:01");
//        System.out.println(DateUtil.between(date1, date2, DateUnit.MINUTE));

//        Date date1 = DateUtil.parse("2024-03-29 00:00:00");
//        Date date2 = DateUtil.parse("2024-04-29 00:00:00");
//        List<DateTime> rangeToList = DateUtil.rangeToList(date1, date2, DateField.DAY_OF_YEAR);
//        for (DateTime dateTime : rangeToList) {
//            System.out.println(dateTime);
//        }

//        Date date1 = DateUtil.parse("2023-01-31 00:00:00");
//        Date date2 = DateUtil.parse("2024-01-31 00:00:00");
//        List<DateTime> rangeToList = CollUtil.newArrayList((Iterable<DateTime>) new DateRange(date1, date2, DateField.MONTH, 2, true, false));
//        for (DateTime dateTime : rangeToList) {
//            System.out.println(dateTime);
//        }

//        Date estimateTime = DateUtil.parse("2022-11-14 08:00:00");
//        Date endTime = DateUtil.parse("2022-11-14 09:16:16");
//        Date estimateTime1 = DateUtil.offset(DateUtil.beginOfDay(estimateTime), DateField.HOUR, 8);
//        Date estimateTime2 = DateUtil.offset(estimateTime1, DateField.DAY_OF_YEAR, -1);
//        System.out.println(estimateTime2);
//        Date endTime1 = DateUtil.offset(DateUtil.beginOfDay(endTime), DateField.HOUR, 8);
//        Date endTime2 = DateUtil.offset(DateUtil.beginOfDay(endTime), DateField.HOUR, 12);
//        double day = DateUtil.between(estimateTime1, endTime1, DateUnit.DAY);
//        if (DateUtil.compare(estimateTime1, estimateTime) > 0) {
//            day = day + 0.5;
//        }
//        if (DateUtil.compare(endTime1, endTime) <= 0) {
//            if (DateUtil.compare(endTime2, endTime) > 0) {
//                day = day + 0.5;
//            } else {
//                day = day + 1;
//            }
//        }
//        System.out.println(day);
    }

}
