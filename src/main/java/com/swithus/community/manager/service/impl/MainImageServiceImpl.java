package com.swithus.community.manager.service.impl;

import com.swithus.community.manager.entity.MainImage;
import com.swithus.community.manager.repository.MainImageRepository;
import com.swithus.community.manager.service.MainImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MainImageServiceImpl implements MainImageService {

    @Value("${main.image.folder}")
    private String imgFolder;

    private final MainImageRepository mainImageRepository;

    @Override
    public void saveImage(String name, String path, String uuid) {
        MainImage mainImage = MainImage.builder()
                .imgName(name)
                .path(path)
                .uuid(uuid)
                .build();
        mainImageRepository.save(mainImage);
    }

    @Override
    public List<String> getImageFiles() {
        List<String> imageFiles = new ArrayList<>();
        File folder = new File(imgFolder);

        if(folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if(files != null){
                for(File file : files){
                    if(file.isFile() && isImageFile(file.getName())){
                        String newFileName = "/images/main/" + file.getName();
                        imageFiles.add(newFileName);
                    }
                }
            }
        }

        return imageFiles;
    }

    private boolean isImageFile(String fileName){
        return fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".png")
                || fileName.toLowerCase().endsWith(".jpeg");
    }
}
