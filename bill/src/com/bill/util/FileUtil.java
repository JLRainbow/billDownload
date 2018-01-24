package com.bill.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class FileUtil {

    /**
     * ʹ��GBK������Ա���ѹ�������ļ�������
     */
    private static final String CHINESE_CHARSET = "GBK";

    /**
     * �ļ���ȡ��������С
     */
    private static final int CACHE_SIZE = 1024;

    /**
     * ��һ���� �� ֧�������ɵ��˵� ���ص�����Ŀ¼
     * 
     * @param path
     *            ֧������Դurl
     * @param filePath
     *            ���ɵ�zip ��Ŀ¼
     * @throws MalformedURLException
     */
    public static void downloadNet(String path, String filePath)
            throws MalformedURLException {
        // ���������ļ�
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL(path);

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(filePath);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unZip(String zipFilePath, String destDir)
            throws Exception {

        ZipFile zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
        Enumeration<?> emu = zipFile.getEntries();
        BufferedInputStream bis;
        FileOutputStream fos;
        BufferedOutputStream bos;
        File file, parentFile;
        ZipEntry entry;
        byte[] cache = new byte[CACHE_SIZE];
        while (emu.hasMoreElements()) {
            entry = (ZipEntry) emu.nextElement();
            if (entry.isDirectory()) {
                new File(destDir + entry.getName()).mkdirs();
                continue;
            }
            bis = new BufferedInputStream(zipFile.getInputStream(entry));
            file = new File(destDir + entry.getName());
            parentFile = file.getParentFile();
            if (parentFile != null && (!parentFile.exists())) {
                parentFile.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos, CACHE_SIZE);
            int nRead = 0;
            while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                fos.write(cache, 0, nRead);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
        }
        zipFile.close();
    }

}
