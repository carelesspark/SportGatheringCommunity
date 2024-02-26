package com.swithus.community.main.controller;

import com.swithus.community.main.dto.UploadResultDTO;
import com.swithus.community.manager.entity.MainImage;
import com.swithus.community.manager.repository.MainImageRepository;
import com.swithus.community.manager.service.MainImageService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MainImageController {

    private final MainImageService mainImageService;

    @Value("${main.image.folder}")
    private String uploadPath;

    @Value("${main.image.delete}")
    private String uploadPath2;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadImg(MultipartFile[] uploadFiles) {
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("이 파일은 이미지 형식이 아닙니다.");

                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("이미지 이름 : " + fileName);

            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + fileName;
            Path savePath = Paths.get(saveName);

            try {
                uploadFile.transferTo(savePath);

                mainImageService.saveImage(fileName, String.valueOf(savePath), uuid);

                resultDTOList.add(new UploadResultDTO(fileName, uuid, uploadPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);

    }

    @GetMapping("/getImagePaths")
    public ResponseEntity<List<String>> getImagePaths() {
        List<String> imagePaths = mainImageService.getImageFiles();
        return new ResponseEntity<>(imagePaths, HttpStatus.OK);
    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam String fileName) {
        try {
            File file = new File(uploadPath + File.separator + fileName);
            byte[] fileContent = Files.readAllBytes(file.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 형식에 맞게 설정

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {


        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + srcFileName);
            boolean result = file.delete();


            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}


