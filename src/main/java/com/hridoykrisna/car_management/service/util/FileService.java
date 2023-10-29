package com.hridoykrisna.car_management.service.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {
    String uploadImage(String path, MultipartFile multipartFile, String name) throws IOException;
    InputStream getResources(String path, String filename) throws FileNotFoundException;
}
