package com.dang.book_shop.service.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageFile {
    String uploadImage(MultipartFile file) throws IOException;

}
