package com.hridoykrisna.car_management.service.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

@Service
public class FileServiceIMPL implements FileService{
    @Override
    public String uploadImage(String path, MultipartFile multipartFile, String name) throws IOException {
        //File Name
        String originalFileName = multipartFile.getOriginalFilename();
        name = name.replace(" ", "_");

        //Random Name Generate
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E_dd_M_yyyy_hh_mm_ss_aa");
        String randomID = simpleDateFormat.format(System.currentTimeMillis());
        assert originalFileName != null;
        String fileName = name+"_"+randomID.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));

        //Full Path
        String filePath = path + fileName;



        //Create Folder if not created
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        //File Copy
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

        return filePath;
    }

    @Override
    public InputStream getResources(String path, String filename) throws FileNotFoundException {
        String finalPath = path+filename;
        return new FileInputStream(finalPath);
    }
}
