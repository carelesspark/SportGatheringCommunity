package com.swithus.community.manager.service;

import java.util.List;

public interface MainImageService {


    void saveImage(String name, String path, String uuid);

    List<String> getImageFiles();


}
