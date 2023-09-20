package me.emate.matefront.storage.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.return-url}")
    private String returnUrl;
    private static final String CONTENTS_URL = "contents";
    private static final String CONTENT_TYPE = "multipart/formed-data";

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        if(Objects.isNull(file) || Objects.isNull(file.getOriginalFilename())) {
            return null;
        }
        String originalFileName = file.getOriginalFilename();
        int imageIdx = originalFileName.lastIndexOf(".");
        String fileEx = originalFileName.substring(imageIdx);
        String targetUrl = bucket + "/" + CONTENTS_URL;
        String fileName = UUID.randomUUID() + fileEx;
        String fileUrl = CONTENTS_URL + "/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(CONTENT_TYPE);
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(targetUrl, fileName, file.getInputStream(), metadata);
        return returnUrl+fileUrl;
    }
}
