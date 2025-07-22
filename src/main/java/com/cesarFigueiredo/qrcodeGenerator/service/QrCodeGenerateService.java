package com.cesarFigueiredo.qrcodeGenerator.service;

import com.cesarFigueiredo.qrcodeGenerator.dto.QrCodeGenerateResponse;
import com.cesarFigueiredo.qrcodeGenerator.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGenerateService {

  private final StoragePort storagePort;

  public QrCodeGenerateService(StoragePort storagePort) {
    this.storagePort = storagePort;
  }

  public QrCodeGenerateResponse generateAndUploadQrCode (String text) throws WriterException, IOException {

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

    ByteArrayOutputStream pngOutpuStream = new ByteArrayOutputStream();
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutpuStream);
    byte[] pngQrCodeData = pngOutpuStream.toByteArray();

    String url = storagePort.uploadFile(pngQrCodeData, UUID.randomUUID().toString(), "img/png");

    return new QrCodeGenerateResponse(url);
  }
}
