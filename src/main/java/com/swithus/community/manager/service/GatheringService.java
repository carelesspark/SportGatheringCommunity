package com.swithus.community.manager.service;

import com.swithus.community.club.entity.Club;
import com.swithus.community.manager.dto.GatheringDTO;
import com.swithus.community.manager.dto.page.GatheringPageRequestDTO;
import com.swithus.community.manager.dto.page.GatheringPageResultDTO;

public interface GatheringService {

    GatheringPageResultDTO<GatheringDTO, Club> getGatheringList(GatheringPageRequestDTO gatheringPageRequestDTO);

    GatheringDTO info(long id);

    default GatheringDTO entityToDto(Club club){
        GatheringDTO dto = GatheringDTO.builder()
                .id(club.getId())
                .regDate(club.getRegDate())
                .modDate(club.getModDate())
                .clubName(club.getName())
                .ctgr(club.getSports().getName())
                .clubLeader(club.getLeader().getNickname())
                .build();

        return dto;

    }


}

