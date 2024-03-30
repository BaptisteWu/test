package com.wubin.test.encrypt.asymmetric;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;

public class TestSM2 {

//    private static final String PRIVATE_KEY = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgzYc3Pp6VNW1LWhgz1RifcMEf0hB4ph9xcKc+K93WpbqgCgYIKoEcz1UBgi2hRANCAASiWUe0vcVURixaRaBXM2FmUiFSGXiaUXOkuzYIwxON4Hc+kGU81Fh7pucpasF5KcF3+dFAa81SD4GBt2X5+lLg";
//    private static final String PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEollHtL3FVEYsWkWgVzNhZlIhUhl4mlFzpLs2CMMTjeB3PpBlPNRYe6bnKWrBeSnBd/nRQGvNUg+Bgbdl+fpS4A==";

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
        SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);
        return sm2.encryptBase64(str, KeyType.PublicKey);
    }

    public static String encode2(String str) {
        SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);
        return sm2.encryptHex(str, KeyType.PublicKey);
    }

    public static String encode3(String str) {
        SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);
        return sm2.encryptBcd(str, KeyType.PublicKey);
    }

    public static String decode(String str) {
        SM2 sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);
        return sm2.decryptStr(str, KeyType.PrivateKey);
    }

    public static void main(String[] args) {
        generateKey();

        //公钥加密，私钥解密

//        System.out.println(encode1("18717758202"));
//        System.out.println(decode("BKKSPTNxLKIq48YH+LFbKg2kCopxso1+tLnd+BWuRmzHVaGYl6Pe7At7Bt4tGkTB/wTvMuXeq+AqdMpuP4Uf4iaGVHWk4av2ixDzer5oUxq1+etwUJNKMNmPTad/Pr9BCNGumNcwABplcfxg"));

//        System.out.println(encode2("18717758202"));
//        System.out.println(decode("04903ac6e4e5a27be2cd1ad82f7d119c68021cbccc4d09a891f5df6a77acba21fb0089faea16b13e24ad6a8be083de6da719814dbf7db94339b4cc0c11fff01da555c3e31b52cfb5dea0cd1e8605821bb3e54c87bbdc8a6d53ebc27760db2e171f726255e8fda275af64b36b"));

//        System.out.println(encode3("18717758202"));
//        System.out.println(decode("0468DF74C48A580B347E4807D159C852CC9AF549E911725DCD979D08A50EA639C72B8E852F0C83871F4A299DAC888AFD1405440A07512FD241C06627974491E3BF5429A6CE9054867F2F69112CC4625DD701C93D284CB7C2CCC0FDBC13DED8AD9617374062EBFB0A3D234820"));

    }

}
