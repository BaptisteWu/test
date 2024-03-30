package com.wubin.test.encrypt;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class TestBase64 {

    public static void toBase64(byte[] bytes) {
        // 会换行
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println(encoder.encode(bytes));

        // 不会换行
        System.out.println(Base64Utils.encodeToString(bytes));

        // 不会换行
        System.out.println(Base64.getEncoder().encodeToString(bytes));
    }

    public static void toStr(String base64) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        System.out.println(new String(decoder.decodeBuffer(base64)));
        System.out.println(new String(Base64Utils.decodeFromString(base64)));
        System.out.println(new String(Base64.getDecoder().decode(base64)));
    }

    public static void toFile(String base64) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        Files.write(Paths.get("D:/test/test999.pdf"), decoder.decodeBuffer(base64));
        Files.write(Paths.get("D:/test/test998.pdf"), Base64Utils.decodeFromString(base64));
        Files.write(Paths.get("D:/test/test997.pdf"), Base64.getDecoder().decode(base64));
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = FileUtil.readString("D:/test/base2.txt", Charset.forName("UTF-8")).getBytes();
        toBase64(bytes);

//        byte[] bytes = FileUtils.readFileToByteArray(new File("D:/test/base2.txt"));
//        toBase64(bytes);

//        toStr("eyJpZCI6MzQyNzUsInVybCI6Imh0dHBzOlwvXC95ZWhpLmxpdmVcLzQxNTMwZGE0NzQ3ZTQxZDhhMTQwOWMwYjI2YmZmNWI2X2ZpbGUuanBnIiwiZW5jcnlwdFVybCI6Imh0dHBzOlwvXC9pbXAuemFidmVxLmNvbVwvNDE1MzBkYTQ3NDdlNDFkOGExNDA5YzBiMjZiZmY1YjZfZmlsZS5qcGciLCJwcmV2aWV3IjoiIiwiY2lkIjoyNCwic2wiOiJodHRwczpcL1wvdDEuemFjdWluLmNvbVwvMjAyMzAzMTFcLzYzakd1SFRxXC9pbmRleC5tM3U4Iiwic2VsZl9jZG5fdXNlIjowLCJzZWxmX2Nkbl9wYXRoIjoiIiwidGl0bGUiOiJcdTRlMmRcdTY1ODdcdTViNTdcdTVlNTUtXHU2OGVlXHU2NWU1XHU1NDExXHU1YjUwXHU3NTI4XHU3ZjhlXHU4MTFhXHU2ZmMwXHU1M2QxXHU2ZDNlXHU5MDYzXHU3OTNlXHU1NDU4XHU1ZTcyXHU1MmIyIiwicGxheWVkQ291bnQiOjI2NDc4LCJkdXJhdGlvbiI6NzQwOS4wNCwidmlwIjowLCJ1cF9udW0iOjI4MSwiZG93bl9udW0iOjM4OCwiZmF2X251bSI6MTI4LCJwcmljZSI6IjAuMDAiLCJ2aXBfcHJpY2UiOiIwLjAwIiwiYnV5X2R1cmF0aW9uIjoxLCJjcmVhdGVfdGltZSI6IjIwMjMtMDMtMTMgMTY6Mzg6MTIiLCJpc19mYXYiOmZhbHNlfQ==");

//        String str = FileUtil.readString("D:/test/base2.txt", Charset.forName("UTF-8"));
//        toFile(str);
    }

}
