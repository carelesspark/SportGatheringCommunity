package com.swithus.community.global.controller;

import com.swithus.community.global.dto.UploadResultDTO;
import com.swithus.community.global.exception.FileUploadException;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Log4j2
@RequestMapping("/image")
public class ImageController {
    private static final String CLUB_MAIN = "club/main/";
    private static final String CLUB_GREETINGS = "club/greetings/";
    private static final String CLUB_POST = "club/post/";
    private static  final String USER_PROFILE = "user/profile/";
    // ~~~/images/
    @Value("${image.folder}")
    private String imageFolder;

    private ResponseEntity<List<UploadResultDTO>> upload(MultipartFile[] uploadFiles, String place) {
        List<UploadResultDTO> uploadResultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
                log.warn("This file is not an image.");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String uuid = UUID.randomUUID().toString();
            String fileName = Paths.get(Objects.requireNonNull(uploadFile.getOriginalFilename())).getFileName().toString();

            log.info("현재 이미지 경로: {} + {}", place, datePath);
            Path path = Paths.get(imageFolder, place, datePath);
            try {
                Files.createDirectories(path);
                Path filePath = path.resolve(uuid + "_" + fileName);
                log.info("Save path: {}", filePath);

                uploadFile.transferTo(filePath.toFile());

                Path thumbnailPath = path.resolve("s_" + uuid + "_" + fileName);
                Thumbnailator.createThumbnail(filePath.toFile(), thumbnailPath.toFile(), 100, 100);

                uploadResultDTOList.add((new UploadResultDTO(place + datePath, uuid, fileName)));
            } catch (IOException e) {
                log.error("Error uploading file", e);
                throw new FileUploadException("Error uploading file", e);
            }
        }

        return new ResponseEntity<>(uploadResultDTOList, HttpStatus.OK);
    }

    @PostMapping("/upload/club/main")
    public ResponseEntity<List<UploadResultDTO>> uploadClubMain(MultipartFile[] uploadFiles) {
        log.info("POST /image/upload/club/main");
        return upload(uploadFiles, CLUB_MAIN);
    }

    @PostMapping("/upload/club/greetings")
    public ResponseEntity<List<UploadResultDTO>> uploadClubGreetings(MultipartFile[] uploadFiles) {
        log.info("POST /image/upload/club/greetings");
        return upload(uploadFiles, CLUB_GREETINGS);
    }

    @PostMapping("/upload/club/post")
    public ResponseEntity<List<UploadResultDTO>> uploadClubPost(MultipartFile[] uploadFiles) {
        log.info("POST /image/upload/club/post");
        return upload(uploadFiles, CLUB_POST);
    }

    @PostMapping("/upload/user/profile")
    public ResponseEntity<List<UploadResultDTO>> uploadUserProfilePost(MultipartFile[] uploadFiles) {
        log.info("POST /image/upload/user/profile");
        return upload(uploadFiles, USER_PROFILE);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> display(String fileName) {
        log.info("GET /image/display?filename={}", fileName);

        String srcFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        log.info("File name: {}", srcFileName);

        File file = new File(imageFolder + srcFileName);

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            byte[] imageBytes = Files.readAllBytes(file.toPath());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(String fileName) {
        log.info("GET /image/delete?filename={}", fileName);

        String srcFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
        log.info("File name: {}", srcFileName);

        Path filePath = Paths.get(imageFolder, srcFileName);
        Path thumbnailPath = Paths.get(filePath.getParent().toString(), "s_" + filePath.getFileName().toString());
        try {
            Files.delete(filePath);
            Files.delete(thumbnailPath);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
