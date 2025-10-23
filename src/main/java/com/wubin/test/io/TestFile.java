package com.wubin.test.io;

import cn.hutool.core.io.FileUtil;
import com.wubin.test.model.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class TestFile {

    public static void main(String[] args) throws IOException {
//        String str1 = "D:/test/test.txt";
//        String str2 = "D:\\test\\test.txt";
        // 项目根路径下
//        File dir = new File("test1");
//        File dir = new File("./test1");
//        System.out.println(Arrays.toString("你好！".getBytes()));
    }

    public static void exists() {
        String fileName = "D:/test/test1/test2/test3/test4/test5";

        // io
        File dir = new File(fileName);
        System.out.println(dir.exists());

        // nio
        System.out.println(Files.exists(Paths.get(fileName)));

        // hutool
        FileUtil.exist(fileName);
    }

    public static void createDir() throws IOException {
        String fileName = "D:/test/test1/test2/test3/test4/test5";

        File dir = new File(fileName);
        // io 会创建父文件夹
        dir.mkdirs();
        // io 不会创建父文件夹，不报错，返回false
        dir.mkdir();

        // nio 不会创建父文件夹，报错
        Files.createDirectory(Paths.get(fileName));
        // nio 会创建父文件夹
        Files.createDirectories(Paths.get(fileName));

        // apache 会创建父文件夹
        FileUtils.forceMkdir(new File(fileName));

        // hutool 会创建父文件夹
        FileUtil.mkdir(fileName);
    }

    public static void rename() throws IOException {
        String fileName1 = "D:/test/en.jpg";
        String fileName2 = "D:/test/test/test.jpg";

        // io 不会创建父文件夹，不报错，返回false
        File file = new File(fileName1);
        file.renameTo(new File(fileName2));

        // nio 不会创建父文件夹，报错
        Files.move(Paths.get(fileName1), Paths.get(fileName2));

        // apache 会创建父文件夹
        FileUtils.moveFile(new File(fileName1), new File(fileName2));

        // hutool 会创建父文件夹
        FileUtil.rename(new File(fileName1), fileName2, true);
    }

    public static void copy() throws IOException {
        String fileName1 = "D:/test/en.jpg";
        String fileName2 = "D:/test/test/test.jpg";

        // io
//        try (InputStream is = new FileInputStream(fileName1);
//             OutputStream os = new FileOutputStream(fileName2)) {
//            byte[] buffer = new byte[1024];
//            int len;
//            while ((len = is.read(buffer)) > 0) {
//                os.write(buffer, 0, len);
//            }
//        }

        // nio
//        try (FileChannel ifc = new FileInputStream(fileName1).getChannel();
//             FileChannel ofc = new FileOutputStream(fileName2).getChannel()) {
//            ofc.transferFrom(ifc, 0, ifc.size());
//        }

        // nio 不会创建父文件夹，报错
        Files.copy(Paths.get(fileName1), Paths.get(fileName2), StandardCopyOption.REPLACE_EXISTING);

        // apache 会创建父文件夹
        FileUtils.copyFile(new File(fileName1), new File(fileName2));

        // hutool 会创建父文件夹
        FileUtil.copy(fileName1, fileName2, true);

        // spring 不会创建父文件夹，报错
        FileCopyUtils.copy(new File(fileName1), new File(fileName2));
    }

    public static void writeByte() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("D:/test/111.jpg"));

        String fileName = "D:/test/test/test/111.jpg";

        // nio 不会创建父文件夹，报错
        Files.write(Paths.get(fileName), bytes);

        // apache 会创建父文件夹
        FileUtils.writeByteArrayToFile(new File(fileName), bytes);
        // apache 不会创建父文件夹，报错
        FileOutputStream fos = new FileOutputStream(fileName);
        IOUtils.write(bytes, fos);
        IOUtils.close(fos);

        // hutool 会创建父文件夹
        FileUtil.writeBytes(bytes, fileName);
    }

    public static void readByte() throws IOException {
        String fileName = "D:/test/111.jpg";

        // nio
        Files.readAllBytes(Paths.get(fileName));

        // apache
        FileUtils.readFileToByteArray(new File(fileName));

        FileInputStream fis = new FileInputStream(fileName);
        IOUtils.toByteArray(fis);
        IOUtils.close(fis);

        // hutool
        FileUtil.readBytes(fileName);
    }

    public static void write() throws IOException {
        String fileName = "D:/test/test1/test2/test2.txt";

        // io
        // 不会创建父文件夹，会创建文件
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            fos.write("Hello World!!!\n你好！！！\n".getBytes());
        }

        try (FileOutputStream fos = new FileOutputStream(fileName, true);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("Hello World!!!\n");
            bw.write("你好！！！");
            bw.newLine();
            bw.flush();
        }

        try (FileWriter fw = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Hello World!!!\n");
            bw.write("你好！！！");
            bw.newLine();
            bw.flush();
        }

        // nio
        // 不会创建父文件夹，会创建文件
        Files.write(
                Paths.get(fileName),
                "Hello World!!!\n你好！！！\n".getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );

        // apache
        // 会创建父文件夹，会创建文件
        FileUtils.write(
                new File(fileName),
                "Hello World!!!\n你好！！！\n",
                "UTF-8",
                true
        );
        // 不会创建父文件夹，会创建文件
        FileOutputStream fos = new FileOutputStream(fileName, true);
        IOUtils.write("Hello World!!!\n你好！！！\n".getBytes(), fos);
        IOUtils.close(fos);
    }

    public static void read() throws IOException {
        String fileName = "D:/test/test1/test2/test2.txt";

        // io
        try (FileInputStream fis = new FileInputStream(fileName)) {
            StringBuilder sb = new StringBuilder();
//            int len;
//            while ((len = input.read()) != -1) {
//                sb.append((char) len);
//            }
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            System.out.println(sb.toString());
        }

        try (FileInputStream fis = new FileInputStream(fileName);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            System.out.println(new String(bos.toByteArray()));
        }

        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        }

        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        }

        // nio
        Files.lines(Paths.get(fileName)).forEach(System.out::println);
        Files.readAllLines(Paths.get(fileName)).forEach(System.out::println);
        System.out.println(new String(Files.readAllBytes(Paths.get(fileName))));

        // apache
        FileUtils.readLines(new File(fileName), "UTF-8").forEach(System.out::println);
        System.out.println(FileUtils.readFileToString(new File(fileName), "UTF-8"));
        System.out.println(new String(FileUtils.readFileToByteArray(new File(fileName))));

        FileInputStream fis = new FileInputStream(fileName);
        IOUtils.readLines(fis, "UTF-8").forEach(System.out::println);
        IOUtils.close(fis);
    }

    public static void size() {
        // 文件大小byte
        File file = new File("D:/test/test1/test2/test2.txt");
        System.out.println(file.length());

        // 文件夹大小byte
        long size = FileUtils.sizeOfDirectory(new File("D:/test/test1"));
        System.out.println(size);
    }

    public static void delete() throws IOException {
        String fileName = "D:/test/test1";

        // io
        // 删除文件或者空的文件夹
        File dir = new File(fileName);
        dir.delete();

        // nio
        // 删除文件或者空的文件夹
        Files.delete(Paths.get(fileName));
        // 遍历删除所有
        Files.walk(Paths.get(fileName))
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    System.out.println(path);
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        Files.walkFileTree(Paths.get(fileName), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("preVisitDirectory : " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visitFile : " + file);
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visitFileFailed : " + file);
                throw exc;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("postVisitDirectory : " + dir);
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });

        // apache
        // 删除文件夹，可非空
        FileUtils.deleteDirectory(new File(fileName));
        // 删除文件或者文件夹，可非空
        FileUtils.deleteQuietly(new File(fileName));
    }

    public static void readAll(File file) {
        System.out.println(file);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File child : files) {
                readAll(child);
            }
        }
    }

    public static void deleteAll(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File child : files) {
                deleteAll(child);
            }
        }
        file.delete();
    }

    public static void filenameFilter(File file) {
        String[] files = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                System.out.println(dir);
                System.out.println(name);
                return false;
            }
        });
        for (String child : files) {
            System.out.println(child);
        }
    }

    public static void fileFilter(File file) {
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                System.out.println(pathname);
                return false;
            }
        });
        for (File child : files) {
            System.out.println(child);
        }
    }

    public static void objectToFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("D:/test/test.o");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(User.builder().id(1).name("张三").birth(new Date()).build());
            oos.flush();
        }
    }

    public static void fileToObject() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("D:/test/test.o");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            User user = (User) ois.readObject();
            System.out.println(user);
        }
    }

}
