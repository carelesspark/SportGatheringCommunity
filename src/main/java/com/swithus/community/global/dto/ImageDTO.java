package com.swithus.community.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private String path;
    private String uuid;
    private String name;

    public String getImageURL() {
        return URLEncoder.encode(path + "/" + uuid + "_" + name, StandardCharsets.UTF_8);
    }

    public String getThumbnailURL() {
        return URLEncoder.encode(path + "/s_" + uuid + "_" + name, StandardCharsets.UTF_8);
    }
}
