package cn.edu.guet.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Author:wangzh
 * Date: 2020/12/31 15:43
 * Version: 1.0
 */
public class EwmUtil {
//    private static final String QR_CODE_IMAGE_PATH = "C:\\Users\\qq\\Desktop\\MyQRCode.png";

    public static BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // BarcodeFormat.QR_CODE：就是QR Code 2D barcode format。是二维码的条纹格式
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

//        Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);

        //            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}
