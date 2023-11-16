package me.emate.matefront.storage.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  String uploadFile(MultipartFile file) throws IOException;
}
