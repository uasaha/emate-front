package me.emate.matefront.storage.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.storage.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class StorageController {

  private final StorageService storageService;

  @PutMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile)
      throws IOException {
    String fileUrl = storageService.uploadFile(multipartFile);

    return ResponseEntity.ok(fileUrl);
  }
}
