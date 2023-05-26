package com.xuecheng.media;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @author Mr.M
 * @version 1.0
 * @description 大文件处理测试
 * @date 2022/9/13 9:21
 */
public class BigFileTest {


    //测试文件分块方法
    @Test
    void testChunk() throws IOException {
        //源文件
        File sourceFile = new File("D:\\yyhfile\\Xproject\\A-Edu\\testFile\\licolis.mp4");
        //chunk地址
        String chunkPath = "D:\\yyhfile\\Xproject\\A-Edu\\testFile\\chunks\\";
        //分块大小
        int chunkSize = 1024 * 1024 * 5;

        //分块数量
        int chunkNum = (int) Math.ceil(sourceFile.length() * 1.0 / chunkSize);
        System.out.println("分块总数：" + chunkNum);

        RandomAccessFile raf_r = new RandomAccessFile(sourceFile, "r");

        for (int i = 0; i < chunkNum; i++) {
            File chunkFile = new File(chunkPath + i);
//            boolean newFile = chunkFile.createNewFile();
            RandomAccessFile raf_rw = new RandomAccessFile(chunkFile, "rw");
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = raf_r.read(buffer)) != -1) {
                raf_rw.write(buffer, 0, len);
                if (chunkFile.length() >= chunkSize) {
                    break;
                }
            }
            raf_rw.close();
            System.out.println("完成分块" + i);
        }
        raf_r.close();
    }


    @Test
    void testMerge() throws IOException {
        //分区文件夹
        File chunkFolder = new File("D:\\yyhfile\\Xproject\\A-Edu\\testFile\\chunks\\");
        //目标文件
        File mergeFile = new File("D:\\yyhfile\\Xproject\\A-Edu\\testFile\\licolis-merge.mp4");
        //源文件
        File sourceFile = new File("D:\\yyhfile\\Xproject\\A-Edu\\testFile\\licolis.mp4");

        RandomAccessFile raf_rw = new RandomAccessFile(mergeFile, "rw");

        File[] files = chunkFolder.listFiles();
        List<File> fileList = Arrays.asList(files);

        fileList.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getName())));
        for (File file : fileList) {
            RandomAccessFile raf_r = new RandomAccessFile(file, "r");
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = raf_r.read(buffer)) != -1) {
                raf_rw.write(buffer, 0, len);
            }
            raf_r.close();
        }
        raf_rw.close();

        FileInputStream fileInputStream = new FileInputStream(mergeFile);
        FileInputStream fileInputStream1 = new FileInputStream(sourceFile);
        String s = DigestUtils.md5Hex(fileInputStream);
        String s1 = DigestUtils.md5Hex(fileInputStream1);
        Assertions.assertEquals(s, s1);
    }
}
