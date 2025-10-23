package com.wubin.test.encrypt.symmetric;

import cn.hutool.core.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class TestAES {

    //AES/ECB/NoPadding
    //AES/ECB/PKCS5Padding
    //AES/CBC/NoPadding
    //AES/CBC/PKCS5Padding
    //NoPadding javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes

    //ECB mode cannot use IV
    public static byte[] ecb(int mode, byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, new SecretKeySpec(key, "AES"));
        return cipher.doFinal(data);
    }

    public static byte[] cbc(int mode, byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);
//        SecretKey secretKey = keyGenerator.generateKey();
//        System.out.println(new String(secretKey.getEncoded()));
//
//        byte[] iv = new byte[16];
//        new SecureRandom().nextBytes(iv);
//        System.out.println(new String(iv));

        //Invalid AES key length: 17 bytes
        System.out.println(Base64.encode(ecb(Cipher.ENCRYPT_MODE, "你好".getBytes(), "96875a9d8fedccae".getBytes())));
        System.out.println(new String(ecb(Cipher.DECRYPT_MODE, Base64.decode("ypSH+LLKpVlyd4mcn/DOuA=="), "96875a9d8fedccae".getBytes())));

        //Wrong IV length: must be 16 bytes long
//        System.out.println(Base64.encode(cbc(Cipher.ENCRYPT_MODE, "你好".getBytes(), "96875a9d8fedccae".getBytes(), "96875a9d8fedccae".getBytes())));
//        System.out.println(new String(cbc(Cipher.DECRYPT_MODE, Base64.decode("aNlOMaPe4k6VNeOrdq2wEg=="), "96875a9d8fedccae".getBytes(), "96875a9d8fedccae".getBytes())));
    }

}
