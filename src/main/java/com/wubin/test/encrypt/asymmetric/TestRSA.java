package com.wubin.test.encrypt.asymmetric;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;

public class TestRSA {

//    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANLgCOah0bGDBCuKnOOPkBY1zEBW/kCZpOHzz8pwYY5C0ngnT8xKVxnMFkk+DJy4WGEHK2ZOllg5jXafGqluorY6JO8GcVAhVr3C/w6jHW+HZ0Px14UNR34zdJBwU0tIj4vs6bYWojQlAcOU4P2ie/xqJE03xICeJ9kpCOlRH6KZAgMBAAECgYAMNJHnUXXq5Zer8n+uryxKkM4T6kUYlZwExQEhqTwISSdX84SLoUDl2hlvH1OJDaV15j2VlBZhIGGm/EIFjQUHVqGjoWI8GBaQbRCzHWVTn/3uCkDqsyMOV6J3BXTHjrDsT2JQgfmxdrNUWZfjzv7vx0Gk0baZFA+yYvEUNEXRgQJBAPoOdjNi9evL2k6RUdStT1bjVMlBW+LKxWOp0yNuGKS71pHpJx7AtPkZPgF8ec0pe8Ti8RhfN7+3ACFQIrC0pUECQQDX4ynG98dvHcJK3T/yVC8mgavTmtZnoZa77f3XZQ2xLJLU07hEuDcETn5CK9CGSzZPni9WQJoY4kyT4WTc629ZAkA4pB/4IdlssP9hPp+Xmi5XkyT4dXqX07CEk8pEw0KHxhijcnmvCJ21sxcNhSGd7GChx3ovOBpLres0oI9LEZBBAkEAwJP+J2POPpugPqIAsg1Qhhv0B+2+RnFJgdi5sFOPlDV7wWgfnwVo5mmSjTqCZ9ld2EX2Wg2EqJ/tyZitHbjS+QJBAIuaV1zzVaDth4ZDoRXASGm0vpVtgtTQuI2RYocgTMb9lyyBOzs4qy0E0lO9V0R5aZlIpGvv0/9QWf2tYcQco20=";
//    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDS4AjmodGxgwQripzjj5AWNcxAVv5AmaTh88/KcGGOQtJ4J0/MSlcZzBZJPgycuFhhBytmTpZYOY12nxqpbqK2OiTvBnFQIVa9wv8Oox1vh2dD8deFDUd+M3SQcFNLSI+L7Om2FqI0JQHDlOD9onv8aiRNN8SAnifZKQjpUR+imQIDAQAB";

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
        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
        return rsa.encryptBase64(str, KeyType.PublicKey);
    }

    public static String encode2(String str) {
        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
        return rsa.encryptHex(str, KeyType.PublicKey);
    }

    public static String encode3(String str) {
        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
        return rsa.encryptBcd(str, KeyType.PublicKey);
    }

    public static String decode(String str) {
        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
        return rsa.decryptStr(str, KeyType.PrivateKey);
    }

    public static void main(String[] args) {
//        generateKey();

        //公钥加密，私钥解密
        //私钥加密，公钥解密

//        System.out.println(encode1("18717758202"));
//        System.out.println(decode("IaH4FCRvYgpMLDXAFV7UGTheqGgNyupfK0/0MbmR7kk29d/vnem0gt3GrQ8pZA4MwgFa/l+NSaSTdK/7GMJdr4TRuIeVVGgL47t6/Cb+x0U0UlVGx1JriEmbQhRALew1o7P7NxBxNCe3vD9IPhlmX7592Nh1vI2fgHPhU5fzVew="));

//        System.out.println(encode2("18717758202"));
//        System.out.println(decode("280c5cbcb2a18896cd5d314c6e4525dd5db3da625a9bd9dcc8e80704134f4070a946a0c2fc8a5cef70f84ca55c08a13a5b84b00f39f7f7e145b0cdc6323eac6b079afbbdf239ec1c8aa359957dece61e8cdf6d4fc4deb54a08e595bf70a7aa21347f3f6d1f45c4411dd25bbc12e7945f71bb21f7c77077c25808e0c002878a72"));

//        System.out.println(encode3("18717758202"));
//        System.out.println(decode("697415896D45C6E34F73A606C17424BC06E3163D39471744C67F8FAA0D1392B17B9AE9D1F4C63E874CA6BAABA7F4B7C0C4B6CEBF1FA196A425141B59DA4560F531AE870954BAAE4E7027008FAF67D753C0A6213D959C53CC678A684E14091C017FD607CBCEC344D6040BFE09F7F66C0A642B45D2BBD7DEB3B9C44F4457282769"));

    }

}
