package com.swithus.community.manager.service;


import java.time.LocalDateTime;
import java.util.List;

public interface ManagerGraphService {

    List<Long> countUserGraph();

    List<String> getDatesList();

    List<Long> countGatheringGraph();
}
