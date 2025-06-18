package com.wubin.test.encrypt.asymmetric;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;

public class TestRSA {

    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKwjeDxZJeMKQHitoO8bXIpthNnlJ5mlqPVd9K2XryD3WhRZB+LYHzBKOl93gloDFb+SDecwAlrKDS/M2K2hHhLyC/ri/OJ4y3AOSlLbF5gayz5mGpG1gE7IjULIu8BVh+RS4DW9QGnVdwUaYY6nx5himlr+0qN7U5GNIxTIUCE/AgMBAAECgYAkNswXShv94UzB9qk6VHPKDZNa8eo8WU0r3anZkWU/FrqlODu//f7Fvn/8UJykSizboiL5pbCBICvZCAv3r0MvmPvXEleUHpDP5XmgzcTHCMdJsI8Z8RZZPI4KU6Ghhq8XXTaKWtmWkKEVOVaDu+xtv+OlCXSrVyaEzSedwg5HeQJBAPFN8RkdnqSVMD2JkQkHf/ZgoQkvEysQ4DavVqpIEBusnm3bt1lQq6Mtx+mDrcKdqHUjj+OhC7g9sXYDSaJXKtkCQQC2nzJPEEizQ9woeZBgihYzbn7zwdjnzlo7GHovcSanOfnTtuFPnYlnpX6ddr5l19jOGvzPRsg8aIxY9Pp5Sy3XAkBc9GdyA2T/hbEFo57JWahDosmmHgpGRsP4jObXxdbeskp9i2w/7cGQpKwkGmOvWshwB3kAeSNon+tiyLVu4iFRAkABVx/GZscx0aCvB11g6AKLtBbocRbHdMNCxcY3zOvMtGc5/5Cdxfk5MoTAYRu6oZ/su1P5rXpQvy+wg3PHKh8XAkARiVQA7kFmnwjwh/IzJgizFdQ+TmENZ4JMalyLkrtqLoeJHbvzEaayaotBcxFE+/bPAjr/WJ5V6yfosUPo1e+l";
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsI3g8WSXjCkB4raDvG1yKbYTZ5SeZpaj1XfStl68g91oUWQfi2B8wSjpfd4JaAxW/kg3nMAJayg0vzNitoR4S8gv64vzieMtwDkpS2xeYGss+ZhqRtYBOyI1CyLvAVYfkUuA1vUBp1XcFGmGOp8eYYppa/tKje1ORjSMUyFAhPwIDAQAB";

    public static void generateKey() {
//        RSA rsa = new RSA();
//        System.out.println(rsa.getPrivateKey());
//        System.out.println(rsa.getPrivateKeyBase64());
//        System.out.println(rsa.getPublicKey());
//        System.out.println(rsa.getPublicKeyBase64());

        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
        System.out.println(Base64Utils.encodeToString(privateKey));
        System.out.println(Base64Utils.encodeToString(publicKey));
    }

    public static String encode1(String str) {
        RSA rsa = new RSA(null, PUBLIC_KEY);
        return rsa.encryptBase64(str, KeyType.PublicKey);
    }

    public static String encode2(String str) {
        RSA rsa = new RSA(null, PUBLIC_KEY);
        return rsa.encryptHex(str, KeyType.PublicKey);
    }

    public static String encode3(String str) {
        RSA rsa = new RSA(null, PUBLIC_KEY);
        return rsa.encryptBcd(str, KeyType.PublicKey);
    }

    public static String decode(String str) {
        RSA rsa = new RSA(PRIVATE_KEY, null);
        return rsa.decryptStr(str, KeyType.PrivateKey);
    }

    public static void main(String[] args) {
//        generateKey();

        //公钥加密，私钥解密
        //私钥加密，公钥解密

//        System.out.println(encode1("18717758202"));
//        System.out.println(decode("qaezf9kfuaOmyXKriDgCldsmElCmBBTo8nHMqiVpcBy2NgfQU85CVhwrdifIoEZAXjvAydKl7iIB96ewpqfD1cr/V8NQtZMJaRknxPLdnU1M+qK3/sxu37P++pZuL406qGsewfKuDLK/4htwU/r7zBoS+pL0jcIaIJyIz3TpbGU="));

//        System.out.println(encode2("18717758202"));
//        System.out.println(decode("8eecbf675718606ad43380272cec26e28bad50d4fe7860d7001ce9301a9056f13ae7d0e12e82f606cd0498a08e7a49d321ff842f14395842baefbd144d7b4f24058052cd25640de45e844a56d5e18c8695ddfe24a5ae1d0cfdb1ab492d8a033552ee3c8ff7d1fa174ae3f6581308b23a59df32a92d427320f720b3a13b4d9c01"));

//        System.out.println(encode3("18717758202"));
//        System.out.println(decode("689824E96BF353CC63DBA91E91A9F258026C96B553A62ADE29614D5D071A7C0F8B777416C68CE6C820A5F34B0377906D96235FF5CD39B30CB3062D79D3F96A30779DAB86E97EB687520303728757855C9FF228149C83B3DAEF30D53818BDAD8EE6097B9CF8683443D4FD6EDB7F55846F7008B4C5F3FDDC7C62E1526F29AF6697"));

        System.out.println(encode1("2025-07-13"));
    }

}
