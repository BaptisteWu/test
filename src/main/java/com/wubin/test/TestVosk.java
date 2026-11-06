package com.wubin.test;

import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.ByteBuffer;

public class TestVosk {

    public static void test() throws IOException, UnsupportedAudioFileException {
        LibVosk.setLogLevel(LogLevel.DEBUG);
        String modelPath = "D:\\work\\test\\vosk-model-small-cn-0.22";
//        String filePath = "D:\\work\\test\\asr_example.wav";
//        String filePath = "D:\\work\\test\\hello_world_male2.wav";
        String filePath = "D:\\work\\test\\hello_world_female2.wav";
        try (Model model = new Model(modelPath);
             InputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(filePath)));
             Recognizer recognizer = new Recognizer(model, 16000)) {
            int nbytes;
            byte[] b = new byte[4096];
            while ((nbytes = ais.read(b)) >= 0) {
                if (recognizer.acceptWaveForm(b, nbytes)) {
                    System.out.println(recognizer.getResult());
                } else {
                    System.out.println(recognizer.getPartialResult());
                }
            }
            System.out.println("-----------------");
            System.out.println(recognizer.getFinalResult());
        }
    }

    public static void test2() throws IOException, LineUnavailableException {
        String modelPath = "D:\\work\\test\\vosk-model-small-cn-0.22";
        AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
        try (TargetDataLine line = AudioSystem.getTargetDataLine(format)) {
            line.open(format);
            line.start();

            Model model = new Model(modelPath);
            Recognizer recognizer = new Recognizer(model, 16000);
            byte[] buffer = new byte[4096];

            while (true) {
                int nbytes = line.read(buffer, 0, 4096);
                if (recognizer.acceptWaveForm(buffer, nbytes)) {
                    System.out.println("最终结果: " + recognizer.getResult());
                } else {
                    System.out.println("临时结果: " + recognizer.getPartialResult());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, InterruptedException, LineUnavailableException {
        test();

//        Line.Info info = null;
//        AudioFormat format = null;
//
//        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
//        for (Mixer.Info mixerInfo : mixerInfos) {
//            System.out.println("mixerInfo:" + mixerInfo);
//            Mixer mixer = AudioSystem.getMixer(mixerInfo);
//            Line.Info[] lineInfos = mixer.getTargetLineInfo();
//            for (Line.Info lineInfo : lineInfos) {
//                if (lineInfo instanceof DataLine.Info) {
//                    DataLine.Info dataInfo = (DataLine.Info) lineInfo;
//                    AudioFormat[] formats = dataInfo.getFormats();
//                    for (AudioFormat audioFormat : formats) {
//                        System.out.println(audioFormat);
//                        TargetDataLine targetDataLine;
//                        try {
//                            targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
//                            targetDataLine.open(audioFormat);
//                            targetDataLine.start();
//                        } catch (Exception e) {
//                            System.out.println("catch");
//                        }
//
////                        if (audioFormat.getSampleSizeInBits() == 16 && audioFormat.getChannels() == 1
////                                && audioFormat.getFrameSize() == 2 && !audioFormat.isBigEndian()) {
////                            info = lineInfo;
////                            format = audioFormat;
////                        }
//                    }
//                }
//            }
//        }

//        AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
//        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
//        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, true);
//        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(audioFormat);
//        targetDataLine.open(audioFormat);
//        targetDataLine.start();

//        AudioInputStream audioStream = new AudioInputStream(targetDataLine);
//        File wavFile = new File("D:\\work\\test\\test3.wav");
//        AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
//        Thread.sleep(5000);
//        targetDataLine.stop();
//        targetDataLine.close();
//        System.out.println("录音结束，文件已保存");

//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        long start = System.currentTimeMillis();
//        while (System.currentTimeMillis() - start < 50000) {
//            int read = targetDataLine.read(buffer.array(), 0, buffer.capacity());
//            if (read > 0) {
//                buffer.limit(read);
//
//                buffer = ByteBuffer.allocate(1024);
//                Thread.sleep(20);
//            }
//        }

//        TargetDataLine line = null;
//        try {
//            line = (TargetDataLine) AudioSystem.getLine(info);
//            line.open(format);
//            line.start();
//            System.out.println("开始录音... (5秒后自动停止)");
//
//            AudioInputStream audioStream = new AudioInputStream(line);
//            File wavFile = new File("D:\\work\\test\\test3.wav");
//            AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
//
//            // 注意：write 方法是一个阻塞操作，只有当数据线中的数据被全部读取后才会返回
//            // 为了让录音持续一段时间，我们让线程休眠5秒后，再手动停止录音
//            // 方案1: 简单方式 - 让录音持续一段时间后停止
//            Thread.sleep(5000);
//
//            // 方案2: 更可控的方式 - 等待用户按键
//            // System.out.println("按回车键停止录音...");
//            // System.in.read();
//
//        } catch (LineUnavailableException e) {
//            System.err.println("无法打开麦克风，请检查麦克风是否被其他程序占用。");
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            System.err.println("录音被中断。");
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.err.println("写入文件时出错。");
//            e.printStackTrace();
//        } finally {
//            if (line != null) {
//                line.stop();
//                line.close();
//                System.out.println("录音结束，文件已保存");
//            }
//        }

//        try {
//            // 使用设备支持的格式来打开线
//            TargetDataLine line = (TargetDataLine) mixer.getLine(lineInfos[0]);
//            // 关键：让系统选择最合适的buffer size
//            // 不要传bufferSize参数，让系统自己选择
//            line.open(format);
//            System.out.println("\n成功打开！使用格式: " + line.getFormat());
//            line.close();
//        } catch (LineUnavailableException e) {
//            System.err.println("打开失败: " + e.getMessage());
//        }
    }

}
