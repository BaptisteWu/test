package com.wubin.test;

import com.fazecast.jSerialComm.SerialPort;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;

public class TestComm {

    public static void measure(SerialPort serialPort) {
        System.out.println("measure");
        byte[] cmd = new byte[60];
        cmd[0] = (byte) 0xbb;
        cmd[1] = 0x01;
        cmd[2] = 0x00;

        for (int i = 3; i <= 57; ++i) {
            cmd[i] = 0x00;
        }

        cmd[58] = (byte) 0xFF;
        byte sum = 0x00;
        for (int i = 0; i <= 58; i++) {
            sum += cmd[i];
        }
        cmd[59] = (byte) (sum & 0xFF);

        System.out.println(Arrays.toString(cmd));
        serialPort.writeBytes(cmd, 60);

        byte[] buffer = new byte[60];
        serialPort.readBytes(buffer, 60);
        System.out.println(Arrays.toString(buffer));
    }

    public static float[] get(SerialPort serialPort, byte cmd2) {
        System.out.println("get:" + cmd2);
        byte[] cmd = new byte[60];
        cmd[0] = (byte) 0xbb;
        cmd[1] = 0x01;
        cmd[2] = cmd2;

        for (int i = 3; i <= 57; ++i) {
            cmd[i] = 0x00;
        }

        cmd[58] = (byte) 0xFF;
        byte sum = 0x00;
        for (int i = 0; i <= 58; i++) {
            sum += cmd[i];
        }
        cmd[59] = (byte) (sum & 0xFF);

        System.out.println(Arrays.toString(cmd));
        serialPort.writeBytes(cmd, 60);

        byte[] buffer = new byte[60];
        serialPort.readBytes(buffer, 60);
        System.out.println(Arrays.toString(buffer));

        byte[] buffer2 = new byte[48];
        System.arraycopy(buffer, 6, buffer2, 0, 48);
        System.out.println(Arrays.toString(buffer2));
        return byteToFloat(buffer2);
    }

    //10°观察者 - XYZ系数 (380-850nm, 10nm间隔, 共48个点)
    private static final double[] COLOR_MATCHING_X = {
            0.0191, 0.0847, 0.2045, 0.3147, 0.3837, 0.3707, 0.3023,
            0.1956, 0.0805, 0.0162, 0.0038, 0.037465, 0.117749,
            0.236491, 0.376772, 0.529826, 0.705224, 0.878655,
            1.01416, 1.11852, 1.12399, 1.03048, 0.856297,
            0.647467, 0.431567, 0.268329, 0.152568, 0.0812606,
            0.0408508, 0.0199413, 0.00957688,
            0.00455263, 0.00217496, 0.00104476, 0.000508258, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
    };

    private static final double[] COLOR_MATCHING_Y = {
            0.0020044, 0.008756, 0.021391, 0.038676, 0.062077,
            0.089456, 0.128201, 0.18519, 0.253589, 0.339133,
            0.460777, 0.606741, 0.761757, 0.875211, 0.961988,
            0.991761, 0.99734, 0.955552, 0.868934, 0.777405,
            0.658341, 0.527963, 0.398057, 0.283493, 0.179828,
            0.107633, 0.060281, 0.0318004, 0.0159051, 0.0077488,
            0.00371774,
            0.00176847, 0.00084619, 0.00040741, 0.00019873, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
    };

    private static final double[] COLOR_MATCHING_Z = {
            0.0860109, 0.389366, 0.972542, 1.55348, 1.96728,
            1.9948, 1.74537, 1.31756, 0.772125, 0.415254,
            0.218502, 0.112044, 0.060709, 0.030451, 0.013676,
            0.003988, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
    };

    private static final double[] ILLUMINANT_D65 = {
            91.486, 93.4318, 86.6823, 104.865, 117.008,
            117.812, 114.861, 115.923, 108.811, 109.354,
            107.802, 104.79, 107.689, 104.405, 104.046,
            100.0, 96.3342, 95.788, 88.6856, 90.0062,
            89.5991, 87.6987, 83.2886, 83.6992, 80.0268,
            80.2146, 82.2778, 78.2842, 69.7213, 71.6091,
            74.35, 61.60, 69.89, 75.09, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
    };

    public static double[] spectrumToXYZ(double[] spectrum) {
        double X = 0.0;
        double Y = 0.0;
        double Z = 0.0;
        for (int i = 0; i < 48; i++) {
            double S = ILLUMINANT_D65[i];
            double xBar = COLOR_MATCHING_X[i];
            double yBar = COLOR_MATCHING_Y[i];
            double zBar = COLOR_MATCHING_Z[i];
            double reflectance = spectrum[i] / 100.0;

            X += S * xBar * reflectance;
            Y += S * yBar * reflectance;
            Z += S * zBar * reflectance;
        }
        //D65
        double k = 0.087647031;
        X *= k;
        Y *= k;
        Z *= k;
        return new double[]{X, Y, Z};
    }

    private static double[] xyzToLAB(double[] xyz) {
        double[] n = {95.58, 100.00, 112.73};

        double xr = xyz[0] / n[0];
        double yr = xyz[1] / n[1];
        double zr = xyz[2] / n[2];

        double fx = f(xr);
        double fy = f(yr);
        double fz = f(zr);

        double L = 116.0 * fy - 16.0;
        double a = 500.0 * (fx - fy);
        double b = 200.0 * (fy - fz);

        return new double[]{
                Math.round(L * 100.0) / 100.0,
                Math.round(a * 100.0) / 100.0,
                Math.round(b * 100.0) / 100.0
        };
    }

    private static double f(double t) {
        if (t > 0.008856) {
            return Math.pow(t, 1.0 / 3.0);
        } else {
            return 7.787 * t + 16.0 / 116.0;
        }
    }

    public static void main(String[] args) throws IOException {
        SerialPort[] ports = SerialPort.getCommPorts();
        for (int i = 0; i < ports.length; i++) {
            System.out.println(i + ": " + ports[i].getSystemPortName());
        }

        SerialPort serialPort = SerialPort.getCommPort("COM3");
        serialPort.setBaudRate(115200);
        serialPort.setNumDataBits(8);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setParity(SerialPort.NO_PARITY);

//        serialPort.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);

        if (serialPort.openPort()) {
            System.out.println("success");

            measure(serialPort);

            byte cmd2 = 0x10;
            double[] spectrum = new double[48];
            for (int i = 0; i <= 3; ++i) {
                float[] floats = get(serialPort, (byte) (cmd2 + i));
                for (int j = 0; j <= 11; ++j) {
                    if (floats[j] < 0) {
                        spectrum[i * 12 + j] = 0;
                    } else if (floats[j] > 100) {
                        spectrum[i * 12 + j] = 100;
                    } else {
                        spectrum[i * 12 + j] = floats[j];
                    }
                }
            }
            System.out.println(Arrays.toString(spectrum));
            double[] xyz = spectrumToXYZ(spectrum);
            double[] lab = xyzToLAB(xyz);
            System.out.println(Arrays.toString(lab));
        }

    }

    public static float[] byteToFloat(byte[] bytes) {
        //默认大端序
//        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        //小端序
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);

        float[] floats = new float[12];
        for (int i = 0; i < 12; i++) {
            floats[i] = buffer.getFloat();
        }

//        FloatBuffer floatBuffer = buffer.asFloatBuffer();
//        floatBuffer.get(floats);

        System.out.println(Arrays.toString(floats));
        return floats;
    }

}
