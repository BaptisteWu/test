package com.wubin.test.encrypt.digest;

import cn.hutool.crypto.SecureUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestMD5 {

    public static String getMD5StrBySelf(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = messageDigest.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0x000000FF) | 0xFFFFFF00).substring(6));
        }
        return sb.toString();
    }

    public static String getMD5StrByCodec(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = messageDigest.digest(str.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "id=1&optionId=2&userId=3&timestamp=1683784751120&key=b26db07c061048ce971ab95804de8e8b";
        System.out.println(getMD5StrBySelf(str));
        System.out.println(getMD5StrByCodec(str));
        System.out.println(DigestUtils.md5Hex(str));
        System.out.println(SecureUtil.md5(str));
        System.out.println(org.springframework.util.DigestUtils.md5DigestAsHex(str.getBytes()));
        System.out.println("047c61c20a7d4982cd26c6eb57d8b224".substring(8, 24).toUpperCase());
    }

}
