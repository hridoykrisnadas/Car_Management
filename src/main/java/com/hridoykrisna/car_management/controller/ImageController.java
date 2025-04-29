package com.hridoykrisna.car_management.controller;

import com.hridoykrisna.car_management.Utils.CommonUtils;
import com.hridoykrisna.car_management.service.util.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.InputStream;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class ImageController {
    private final FileService fileService;


    private final String imagePath = CommonUtils.ImagePath;

    @GetMapping(value = "/images/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("image") String imageName,
                              HttpServletResponse response)
            throws IOException {

        InputStream stream = this.fileService.getResources(imagePath, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream, response.getOutputStream());
    }
}
