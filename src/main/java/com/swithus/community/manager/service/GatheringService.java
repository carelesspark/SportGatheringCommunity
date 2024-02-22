package com.swithus.community.manager.service;

import com.swithus.community.club.entity.Club;
import com.swithus.community.manager.dto.GatheringDTO;
import com.swithus.community.manager.dto.WithdrawalGatheringDTO;
import com.swithus.community.manager.dto.page.GatheringPageRequestDTO;
import com.swithus.community.manager.dto.page.GatheringPageResultDTO;
import com.swithus.community.manager.entity.WithdrawalGathering;

public interface GatheringService {

    GatheringPageResultDTO<GatheringDTO, Club> getGatheringList(GatheringPageRequestDTO gatheringPageRequestDTO);

    GatheringDTO info(long id);

    void withdrawalGathering(WithdrawalGatheringDTO withdrawalGatheringDTO);

    GatheringPageResultDTO<WithdrawalGatheringDTO, WithdrawalGathering> getDeletedGatheringList(GatheringPageRequestDTO gatheringPageRequestDTO);

    WithdrawalGatheringDTO infoDeletedGathering(long no);

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

    default WithdrawalGathering dtoToEntity(WithdrawalGatheringDTO withdrawalGatheringDTO){
        WithdrawalGathering entity = WithdrawalGathering.builder()
                .id(withdrawalGatheringDTO.getId())
                .deleteReason(withdrawalGatheringDTO.getDeleteReason())
                .leader(withdrawalGatheringDTO.getLeaderName())
                .name(withdrawalGatheringDTO.getClubName())
                .build();

        return entity;
    }

    default WithdrawalGatheringDTO entityToWithdrawalGatheringDTO(WithdrawalGathering withdrawalGathering){
        WithdrawalGatheringDTO withdrawalGatheringDTO = WithdrawalGatheringDTO.builder()
                .id(withdrawalGathering.getId())
                .regDate(withdrawalGathering.getRegDate())
                .deleteReason(withdrawalGathering.getDeleteReason())
                .leaderName(withdrawalGathering.getLeader())
                .clubName(withdrawalGathering.getName())
                .build();

        return withdrawalGatheringDTO;
    }


}

