package com.ywq.ylib.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * @author yanwenqiang
 * @Date 15-7-23
 * @description Gzip压缩
 */
public class GZipUtil {
    //    private static final int BUFFER = 2 * 1024;// 设置缓存大小2K

    /**
     * GZip解压缩 <br>
     */
    public static String decompressGZip(byte[] data) throws IOException {
        InputStream in;
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        StringBuilder sb = new StringBuilder();
        byte[] h = new byte[2];
        h[0] = data[0];
        h[1] = data[1];
        int headerData = (h[0] << 8) | h[1] & 0xFF;
        // Gzip 流 的前两个字节是 0x1f8b
        if (headerData != -1 && headerData == 0x1f8b) {
            in = new GZIPInputStream(bis);
        } else {
            in = bis;
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(in),
                1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        in.close();
        bis.close();
        in.close();
        return sb.toString();
    }

    /**
     * 数据压缩
     */
//    public static void compress(InputStream is, OutputStream os)
//            throws Exception {
//
//        GZIPOutputStream gos = new GZIPOutputStream(os);
//
//        int count;
//        byte data[] = new byte[BUFFER];
//        while ((count = is.read(data, 0, BUFFER)) != -1) {
//            gos.write(data, 0, count);
//        }
//
//        gos.finish();
//
//        gos.flush();
//        gos.close();
//    }

    /**
     * 数据解压缩
     */
//    public static void decompress(InputStream is, OutputStream os)
//            throws Exception {
//
//        GZIPInputStream gis = new GZIPInputStream(is);
//
//        int count;
//        byte data[] = new byte[BUFFER];
//        while ((count = gis.read(data, 0, BUFFER)) != -1) {
//            os.write(data, 0, count);
//        }
//
//        gis.close();
//    }
}
