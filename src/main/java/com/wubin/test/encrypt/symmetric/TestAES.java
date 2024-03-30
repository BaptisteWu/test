package com.wubin.test.encrypt.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class TestAES {

    private static final String KEY_ALGORITHM = "AES";

    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static Key getKey(String key) throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
//        keyGenerator.init(128, new SecureRandom(key.getBytes()));
//        return keyGenerator.generateKey();
        return new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
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

    public static void main(String[] args) throws Exception {
        System.out.println(encode("测试test", "96875a9d8fedccae"));
        System.out.println(decode("IynybiF5fU4qjuem/C5Kvw==", "96875a9d8fedccae"));
    }

}
