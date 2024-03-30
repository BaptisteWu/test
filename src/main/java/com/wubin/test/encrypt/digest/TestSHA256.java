package com.wubin.test.encrypt.digest;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestSHA256 {

    public static String getSHA256StrBySelf(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = messageDigest.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0x000000FF) | 0xFFFFFF00).substring(6));
        }
        return sb.toString();
    }

    public static String getSHA256StrByCodec(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = messageDigest.digest(str.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "201909020001raIHcQlM2umJfVf+xzitNQ==1568383635245";
        System.out.println(getSHA256StrBySelf(str));
        System.out.println(getSHA256StrByCodec(str));
        System.out.println(DigestUtils.sha256Hex(str));
        System.out.println(SecureUtil.sha256(str));
    }

}
