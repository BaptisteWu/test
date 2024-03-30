package com.wubin.test.encrypt.symmetric;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Base64;

public class TestPBE {

    private static final String ALGORITHM = "PBEWithMD5AndDES";

    public static Key getKey(String key) throws Exception {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return secretKeyFactory.generateSecret(pbeKeySpec);
    }

    public static String encode(String data, String key, String salt) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key), new PBEParameterSpec(salt.getBytes(), 100));
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decode(String data, String key, String salt) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key), new PBEParameterSpec(salt.getBytes(), 100));
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(bytes);
    }

    public static void main(String[] args) throws Exception {
        //Salt must be 8 bytes long
        System.out.println(encode("测试test", "test", "12345678"));
        System.out.println(decode("AXOMnsnIzykyKQhCmShKqg==", "test", "12345678"));
    }

}
