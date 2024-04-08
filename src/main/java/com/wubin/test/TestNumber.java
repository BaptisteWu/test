package com.wubin.test;

import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.math.Fraction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class TestNumber {

    public static void equalTest() {
        // 装箱，相当于 Integer.valueOf();
        Integer a = 1;
        // 拆箱，相当于a.intValue();
        int b = a;

        Integer c1 = 100;
        Integer c2 = 100;
        System.out.println(c1 == c2); // true

        // -128到127之间的值,被装箱后，会被放在内存里进行重用
        // 如果超出了这个范围,系统会重新new一个对象
        Integer d1 = 200;
        Integer d2 = 200;
        System.out.println(d1 == d2); // false
    }

    public static void bigDecimal() {
        // BigDecimal.ROUND_UP          入
        // BigDecimal.ROUND_DOWN        舍
        // BigDecimal.ROUND_CEILING     bigDecimal为正与ROUND_UP相同，bigDecimal为负与ROUND_DOWN相同
        // BigDecimal.ROUND_FLOOR       bigDecimal为负与ROUND_UP相同，bigDecimal为正与ROUND_DOWN相同
        // BigDecimal.ROUND_HALF_UP     四舍五入
        // BigDecimal.ROUND_HALF_DOWN   五舍六入
        // BigDecimal.ROUND_HALF_EVEN   四舍六入，五分两种情况，前一位为奇数，则入位，否则舍
        // BigDecimal.ROUND_UNNECESSARY
        BigDecimal bigDecimal = new BigDecimal("1.2345");
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_UP));
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_CEILING));
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_FLOOR));
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP));
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_HALF_EVEN));
//        System.out.println(bigDecimal.setScale(3, BigDecimal.ROUND_UNNECESSARY));

//        bigDecimal.divide(bigDecimal, 2, BigDecimal.ROUND_UP);
    }

    public static void radix() {
        // 16进制 0x开头
        System.out.println(Integer.toHexString(255));
        System.out.println(Integer.toHexString(0xff));
        System.out.println(Integer.parseInt("ff", 16));
        System.out.println(new BigInteger("ff", 16));

        // 8进制 0开头
        System.out.println(Integer.toOctalString(9));
        System.out.println(Integer.toOctalString(011));
        System.out.println(Integer.parseInt("11", 8));
        System.out.println(new BigInteger("11", 8));

        // 2进制 0b开头
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(0b11));
        System.out.println(Integer.parseInt("11", 2));
        System.out.println(new BigInteger("11", 2));
    }

    public static void fractionalOperation() {
        Fraction a = Fraction.getFraction("1/3");
        Fraction b = Fraction.getFraction(1, 4);
        Fraction c = Fraction.getFraction(1);
        System.out.println(c.subtract(a.add(b)));
        System.out.println(a);
    }

    public static void main(String[] args) {
//        int a = 255;
//        byte b = (byte) a;
//        System.out.println(a);
//        System.out.println(b);

//        BigDecimal bigDecimal = new BigDecimal(1234d);
//        BigDecimal bigDecimal = new BigDecimal("1234");
//        BigDecimal bigDecimal = BigDecimal.valueOf(1234d);
//        System.out.println(bigDecimal.toPlainString());
//        System.out.println(bigDecimal.toEngineeringString());
//        System.out.println(bigDecimal.toString());
//        System.out.println(NumberUtil.toStr(bigDecimal));

//        DecimalFormat decimalFormat = new DecimalFormat("000");
//        System.out.println(decimalFormat.format(1));
//        System.out.println(decimalFormat.format(new BigDecimal("1")));

//        System.out.println(NumberUtil.decimalFormat("00.00", 1));
//        System.out.println(NumberUtil.decimalFormat("00.00", new BigDecimal("1")));

//        System.out.println(NumberUtil.ceilDiv(239, 60));

        System.out.println(BigDecimal.valueOf(61).divide(BigDecimal.valueOf(60), BigDecimal.ROUND_UP));
        System.out.println(BigDecimal.valueOf(61).divide(BigDecimal.valueOf(60), 0, BigDecimal.ROUND_UP));

    }

}
