package com.swithus.community.board.service;

import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.dto.page.PageResultDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.dto.page.FaqPageResultDTO;
import com.swithus.community.manager.entity.Faq;
import com.swithus.community.user.entity.User;

import java.util.List;

public interface PromotionBoardService {

    List<Promotion> findTop4ByOrderByRegDateDesc();

    boolean checkClubLeader(String nickname);

    PageResultDTO<PromotionBoardDTO, Promotion> getPromotionList(PageRequestDTO requestDTO);

    void write(PromotionBoardDTO dto);

    PromotionBoardDTO info(Long no);

    void modify(PromotionBoardDTO dto);

    void deletePromotion(Long no);


    default PromotionBoardDTO entityToDto(Promotion promotion){

        PromotionBoardDTO dto = PromotionBoardDTO.builder().
                id(promotion.getId())
                .title(promotion.getTitle())
                .content(promotion.getContent())
                .clubCtgr(promotion.getClub().getSports().getName())
                .clubName(promotion.getClub().getName())
                .clubId(promotion.getClub().getId())
                .writer(promotion.getClub().getLeader().getNickname())
                .visitCount(promotion.getVisitCount())
                .nickname(promotion.getClub().getLeader().getNickname())
                .regDate(promotion.getRegDate())
                .modDate(promotion.getModDate())
                .build();

        return dto;
    }


}
