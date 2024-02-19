package com.swithus.community.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class UploadResultDTO {
    private String folderPath;
    private String uuid;
    private String fileName;

    public String getImageURL() {
        return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, StandardCharsets.UTF_8);
    }

    public String getThumbnailURL() {
        return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, StandardCharsets.UTF_8);
    }
}
