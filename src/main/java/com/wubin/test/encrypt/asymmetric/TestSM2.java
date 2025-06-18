package com.wubin.test.encrypt.asymmetric;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;

public class TestSM2 {

    private static final String PRIVATE_KEY = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgPO2orDr8U6G6ct0VhYWEm4vnzW56IrJnBnlgnUUb0W2gCgYIKoEcz1UBgi2hRANCAASFaqf69+rTQqHgKzowu7t5rFTkPVH6xq7Azn6jV2wl4zkC9QWC3NI/XiStAqb/vlhwoIrGlYyW6Sccl4woQ9hQ";
    private static final String PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEhWqn+vfq00Kh4Cs6MLu7eaxU5D1R+sauwM5+o1dsJeM5AvUFgtzSP14krQKm/75YcKCKxpWMluknHJeMKEPYUA==";

    public static void generateKey() {
//        SM2 sm2 = SmUtil.sm2();
//        System.out.println(sm2.getPrivateKey());
//        System.out.println(sm2.getPrivateKeyBase64());
//        System.out.println(sm2.getPublicKey());
//        System.out.println(sm2.getPublicKeyBase64());

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
        System.out.println(Base64Utils.encodeToString(privateKey));
        System.out.println(Base64Utils.encodeToString(publicKey));
        System.out.println(HexUtil.encodeHex(privateKey));
        System.out.println(HexUtil.encodeHex(publicKey));
    }

    public static String encode1(String str) {
        SM2 sm2 = SmUtil.sm2(null, PUBLIC_KEY);
        return sm2.encryptBase64(str, KeyType.PublicKey);
    }

    public static String encode2(String str) {
        SM2 sm2 = SmUtil.sm2(null, PUBLIC_KEY);
        return sm2.encryptHex(str, KeyType.PublicKey);
    }

    public static String encode3(String str) {
        SM2 sm2 = SmUtil.sm2(null, PUBLIC_KEY);
        return sm2.encryptBcd(str, KeyType.PublicKey);
    }

    public static String decode(String str) {
        SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, null);
        return sm2.decryptStr(str, KeyType.PrivateKey);
    }

    public static void main(String[] args) {
//        generateKey();

        //公钥加密，私钥解密

//        System.out.println(encode1("18717758202"));
//        System.out.println(decode("BAqKCBgP9zmBFKX0PCF/RrGtNxLCWK3bVO8evR+THJ7ibwgfwYEJ2utJBWKWLpn1STABu/IyHpq1GnjLboxvrVocYcKgc1Izi+T5VK9KYZZn7EaT2u93xJ34uODKgGclWAHP4IYJnQFgvkRT"));

//        System.out.println(encode2("18717758202"));
//        System.out.println(decode("04e6ca58e8259aa5250dddaa0e78b0db8de913c7685f831c8b2a391e3f2344d4ff9c6dd6e5877cf84d8859b9037a53224ca6dcbf16c7b321aa048d8f75f5ef1fc36f432901a11d24e06551515e5c8daa073a1b5598a9a74f137d45295bd8fc76ba712e9394c47e1a848ab556"));

//        System.out.println(encode3("18717758202"));
//        System.out.println(decode("04CB53DD8F7BC4052DF3332B2EE6A88FBC264B6D94A2CDD768594AA27752325D6D9D07226678FED0B41FD2F69E71D01D6A7A0712B788708DA6CA88A80522AF74A93897CF6CEAF8B075FFE726E8AD357757804DF18248C6DD501463FD296A90BD969D532423FDD6A071359FB8"));

    }

}
