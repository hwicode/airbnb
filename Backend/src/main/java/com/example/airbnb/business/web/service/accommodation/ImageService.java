package com.example.airbnb.business.web.service.accommodation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final UploadService uploadService;

    @Transactional
    public String image(MultipartFile file) throws IOException {
        return uploadService.uploadFile(file);
    }

}
