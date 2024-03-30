package com.wubin.test;

import com.wubin.test.model.User;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class TestBytes {

    public static byte[] strToBytes(String str) {
        return str.getBytes();
    }

    public static String bytesToStr(byte[] bytes) {
        return new String(bytes);
    }

    public static byte[] hexToBytes(String hex) throws DecoderException {
        return Hex.decodeHex(hex);
    }

    public static String bytesToHex(byte[] bytes) {
        return Hex.encodeHexString(bytes, false);
    }

    public static byte[] objectToBytes(Object o) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(o);
            oos.flush();
            return bos.toByteArray();
        }
    }

    public static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        }
    }

    public static byte[] intToBytes1(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i & 0xff);
        bytes[1] = (byte) (i >> 8 & 0xff);
        bytes[2] = (byte) (i >> 16 & 0xff);
        bytes[3] = (byte) (i >> 24 & 0xff);
        return bytes;
    }

    public static byte[] intToBytes2(int i) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (i & 0xff);
        bytes[2] = (byte) (i >> 8 & 0xff);
        bytes[1] = (byte) (i >> 16 & 0xff);
        bytes[0] = (byte) (i >> 24 & 0xff);
        return bytes;
    }

    public static int bytesToInt1(byte[] bytes) {
        int int1 = bytes[0] & 0xff;
        int int2 = (bytes[1] & 0xff) << 8;
        int int3 = (bytes[2] & 0xff) << 16;
        int int4 = (bytes[3] & 0xff) << 24;
        return int1 | int2 | int3 | int4;
    }

    public static int bytesToInt2(byte[] bytes) {
        int int1 = bytes[3] & 0xff;
        int int2 = (bytes[2] & 0xff) << 8;
        int int3 = (bytes[1] & 0xff) << 16;
        int int4 = (bytes[0] & 0xff) << 24;
        return int1 + int2 + int3 + int4;
    }

    public static byte[] floatToBytes1(float f) {
        int intBits = Float.floatToIntBits(f);
        return intToBytes1(intBits);
    }

    public static float bytesToFloat1(byte[] bytes) {
        int intBits = bytesToInt1(bytes);
        return Float.intBitsToFloat(intBits);
    }

    public static byte[] floatToBytes2(float f) {
        int intBits = Float.floatToIntBits(f);
        return intToBytes2(intBits);
    }

    public static float bytesToFloat2(byte[] bytes) {
        int intBits = bytesToInt2(bytes);
        return Float.intBitsToFloat(intBits);
    }

    public static void main(String[] args) throws DecoderException, IOException, ClassNotFoundException {
//        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
//        byte[] copy = new byte[4];
//        System.arraycopy(bytes, 3, copy, 0, copy.length);
//        System.out.println(Arrays.toString(copy));

//        System.out.println(Arrays.toString(strToBytes("欢迎来到我的世界")));
//        System.out.println(bytesToStr(new byte[]{-26, -84, -94, -24, -65, -114, -26, -99, -91, -27, -120, -80, -26, -120, -111, -25, -102, -124, -28, -72, -106, -25, -107, -116}));

        System.out.println(Arrays.toString(hexToBytes("E6ACA2E8BF8EE69DA5E588B0E68891E79A84E4B896E7958C")));
        System.out.println(bytesToHex(new byte[]{-26, -84, -94, -24, -65, -114, -26, -99, -91, -27, -120, -80, -26, -120, -111, -25, -102, -124, -28, -72, -106, -25, -107, -116}));

//        System.out.println(Arrays.toString(objectToBytes(User.builder().id(1).name("张三").birth(new Date()).build())));
//        System.out.println((User) bytesToObject(new byte[]{-84, -19, 0, 5, 115, 114, 0, 25, 99, 111, 109, 46, 119, 117, 98, 105, 110, 46, 116, 101, 115, 116, 46, 109, 111, 100, 101, 108, 46, 85, 115, 101, 114, 33, 73, -112, 123, -83, 53, -82, -105, 2, 0, 3, 76, 0, 5, 98, 105, 114, 116, 104, 116, 0, 16, 76, 106, 97, 118, 97, 47, 117, 116, 105, 108, 47, 68, 97, 116, 101, 59, 76, 0, 2, 105, 100, 116, 0, 19, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 73, 110, 116, 101, 103, 101, 114, 59, 76, 0, 4, 110, 97, 109, 101, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 120, 112, 115, 114, 0, 14, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 68, 97, 116, 101, 104, 106, -127, 1, 75, 89, 116, 25, 3, 0, 0, 120, 112, 119, 8, 0, 0, 1, 118, 111, 66, -52, -108, 120, 115, 114, 0, 17, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 73, 110, 116, 101, 103, 101, 114, 18, -30, -96, -92, -9, -127, -121, 56, 2, 0, 1, 73, 0, 5, 118, 97, 108, 117, 101, 120, 114, 0, 16, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 78, 117, 109, 98, 101, 114, -122, -84, -107, 29, 11, -108, -32, -117, 2, 0, 0, 120, 112, 0, 0, 0, 1, 116, 0, 6, -27, -68, -96, -28, -72, -119}));

//        System.out.println(Arrays.toString(intToBytes1(10100025)));
//        System.out.println(bytesToInt1(new byte[]{57, 29, -102, 0}));
//        System.out.println(Arrays.toString(intToBytes2(10100025)));
//        System.out.println(bytesToInt1(new byte[]{0, -102, 29, 57}));

//        System.out.println(Arrays.toString(floatToBytes1(1.3f)));
//        System.out.println(bytesToFloat1(new byte[]{102, 102, -90, 63}));
//        System.out.println(Arrays.toString(floatToBytes2(1.3f)));
//        System.out.println(bytesToFloat2(new byte[]{63, -90, 102, 102}));
    }

}
