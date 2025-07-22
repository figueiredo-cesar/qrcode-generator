package com.cesarFigueiredo.qrcodeGenerator.controller;

import com.cesarFigueiredo.qrcodeGenerator.dto.QrCodeGenerateRequest;
import com.cesarFigueiredo.qrcodeGenerator.dto.QrCodeGenerateResponse;
import com.cesarFigueiredo.qrcodeGenerator.service.QrCodeGenerateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

  private final QrCodeGenerateService qrCodeGenerateService;

  public QrCodeController(QrCodeGenerateService qrCodeGenerateService){
    this.qrCodeGenerateService = qrCodeGenerateService;
  }

  @PostMapping
  public ResponseEntity<QrCodeGenerateResponse> generate (@RequestBody QrCodeGenerateRequest resquest) {
    try {
      QrCodeGenerateResponse qrCodeGenerateResponse = this.qrCodeGenerateService.generateAndUploadQrCode(resquest.text());
      return ResponseEntity.ok(qrCodeGenerateResponse);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
