package com.wubin.test.encrypt.symmetric;

import cn.hutool.core.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TestPBE {

    public static SecretKey getKey(String key) throws Exception {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        return secretKeyFactory.generateSecret(pbeKeySpec);
    }

    public static byte[] pbe(int mode, byte[] data, byte[] key, byte[] salt) throws Exception {
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(mode, new SecretKeySpec(key, "PBEWithMD5AndDES"), new PBEParameterSpec(salt, 100));
//        cipher.init(mode, getKey(new String(key)), new PBEParameterSpec(salt, 100));
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        //Salt must be 8 bytes long
        System.out.println(Base64.encode(pbe(Cipher.ENCRYPT_MODE, "你好".getBytes(), "test".getBytes(), "12345678".getBytes())));
        System.out.println(new String(pbe(Cipher.DECRYPT_MODE, Base64.decode("IzkjkKyrDkc="), "test".getBytes(), "12345678".getBytes())));
    }

}
