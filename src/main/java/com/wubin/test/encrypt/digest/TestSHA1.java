package com.wubin.test.encrypt.digest;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestSHA1 {

    public static String getSHA1StrBySelf(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = messageDigest.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0x000000FF) | 0xFFFFFF00).substring(6));
        }
        return sb.toString();
    }

    public static String getSHA1StrByCodec(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = messageDigest.digest(str.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "a03a5f5d8fed0e6a650264b483a7efadappIdzx20181129155010iccid8986111820803855290timestamp1567151345843v2.0a03a5f5d8fed0e6a650264b483a7efad";
        System.out.println(getSHA1StrBySelf(str));
        System.out.println(getSHA1StrByCodec(str));
        System.out.println(DigestUtils.sha1Hex(str));
        System.out.println(SecureUtil.sha1(str));
    }

}
