package com.wubin.test.encrypt.symmetric;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

public class TestDES {

    private static final String KEY_ALGORITHM = "DES";

    // DES/ECB/NoPadding
    // DES/ECB/PKCS5Padding,DES/ECB/PKCS7Padding
    // DES/ECB/ISO10126Padding
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
//    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

//    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS7Padding";
//    static {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//    }

    public static Key getKey(String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return secretKeyFactory.generateSecret(desKeySpec);
    }

    public static String encode(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decode(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key));
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(bytes);
    }

    public static String encodeWithIV(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key), new IvParameterSpec(iv.getBytes()));
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decodeWithIV(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key), new IvParameterSpec(iv.getBytes()));
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(bytes);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encode("测试test", "12345678"));
        System.out.println(decode("li8QlZlgiOugwXLjYYorKA==", "12345678"));

        //ECB mode cannot use IV
        //Wrong IV length: must be 8 bytes long
//        System.out.println(encodeWithIV("测试test", "12345678", "12345678"));
//        System.out.println(decodeWithIV("Tiqxct9ArohXBo/6PyCBxw==", "12345678", "12345678"));
    }

}
