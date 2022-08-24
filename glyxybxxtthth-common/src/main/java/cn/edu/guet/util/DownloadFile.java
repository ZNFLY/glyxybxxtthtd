package cn.edu.guet.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Young Kbt
 * @date 2021/12/20 22:04
 * @description 下载文件
 */
public class DownloadFile {

    /**
     * 传入文件路径，下载 Excel 文件
     * 适用于 事先把文件放到电脑的某个路径下
     * @param response 响应
     * @param file 文件
     * @param fileName 文件名
     */
    public static void DownloadExcelByFile(HttpServletResponse response, File file, String fileName) {
        try {
            DownloadExcelByIO(response, new FileInputStream(file), fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传入文件地址的流，下载 Excel 文件
     * 适用于获取 resource 的文件，因为生产环境无法直接通过文件路径获取，文件路径只适用于开发
     * @param response 响应
     * @param fileInputStream 文件流
     * @param fileName 文件名
     */
    public static void DownloadExcelByIO(HttpServletResponse response, InputStream fileInputStream, String fileName) {
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes()));   // 设置文件名
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(fileInputStream);
            OutputStream os = response.getOutputStream();
            int len;
            while (-1 != (len = bis.read(buffer, 0, buffer.length))) {
                os.write(buffer, 0, len);
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
