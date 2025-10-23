package com.wubin.test.encrypt.symmetric;

import cn.hutool.core.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class TestDES {

    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS7Padding";
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    //DES/ECB/NoPadding
    //DES/ECB/PKCS5Padding
    //DES/CBC/NoPadding
    //AES/CBC/PKCS5Padding
    //NoPadding javax.crypto.IllegalBlockSizeException: Input length not multiple of 8 bytes

    public static SecretKey getKey(byte[] key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        return secretKeyFactory.generateSecret(desKeySpec);
    }

    //ECB mode cannot use IV
    public static byte[] ecb(int mode, byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(mode, new SecretKeySpec(key, "DES"));
//        cipher.init(mode, getKey(key));
        return cipher.doFinal(data);
    }

    public static byte[] cbc(int mode, byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(mode, new SecretKeySpec(key, "DES"), new IvParameterSpec(iv));
//        cipher.init(mode, getKey(key), new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        //Wrong key size
        System.out.println(Base64.encode(ecb(Cipher.ENCRYPT_MODE, "你好".getBytes(), "12345678".getBytes())));
        System.out.println(new String(ecb(Cipher.DECRYPT_MODE, Base64.decode("QO2klVpoYT8="), "12345678".getBytes())));

        //Wrong IV length: must be 8 bytes long
//        System.out.println(Base64.encode(cbc(Cipher.ENCRYPT_MODE, "你好".getBytes(), "12345678".getBytes(), "12345678".getBytes())));
//        System.out.println(new String(cbc(Cipher.DECRYPT_MODE, Base64.decode("8nuUCN9O6kQ="), "12345678".getBytes(), "12345678".getBytes())));
    }

}
