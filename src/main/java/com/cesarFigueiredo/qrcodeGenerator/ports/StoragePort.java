package com.cesarFigueiredo.qrcodeGenerator.ports;

public interface StoragePort {
  String uploadFile (byte[] fileData, String filename, String contentType);
}
