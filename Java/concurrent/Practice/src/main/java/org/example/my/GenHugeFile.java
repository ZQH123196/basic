package org.example.my;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 我的想法是，
 * <p>
 * basic：
 * using multiple way to generate big file.
 * <p>
 * <p>
 * pro：
 * 使用 JMH benchmark 得到更精确的压力跟测试结果
 */
public class GenHugeFile {
    static long size1kb = 1024;
    static long size1m = 1024 * size1kb;
    static long size1g = 1024 * size1m;
    static long size2g = 2 * size1g;


    @Test
    void test1G() throws IOException {
        simpleWay();
    }

    private void simpleWay() throws IOException {
        String targetDir = "D:\\Inbox\\caches\\";
        String targetFileName = "1G.txt";
        Path targetFilePath = Paths.get(targetDir + targetFileName).normalize();

        Files.deleteIfExists(targetFilePath);
        Files.createFile(targetFilePath);

        writeFile(targetFilePath);

    }


    static final int BUFFER_SIZE = (int) (32 * size1m);

    private void writeFile(Path targetFilePath) throws IOException {
        byte byte1 = "1".getBytes()[0];

        try (OutputStream out = Files.newOutputStream(targetFilePath)) {
            byte[] bytes = new byte[BUFFER_SIZE];
            Arrays.fill(bytes, byte1);
            long len = size2g;
            while (len > 0) {
                out.write(bytes);
                len -= BUFFER_SIZE;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void nioWay() {
        try {
            File file = new File("D:\\Inbox\\caches\\largefile.txt");
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 32);
            long fileSize = size2g; // 2GB

            long bytesWritten = 0;
            while (bytesWritten < fileSize) {
                buffer.rewind();
                channel.write(buffer);
                bytesWritten += buffer.capacity();
            }
            channel.close();
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
