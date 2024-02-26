package com.swithus.community.board.promotion.service;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.promotion.dto.PromotionBoardDTO;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.user.entity.User;

import java.util.List;

public interface PromotionBoardService {

    List<Promotion> findTop4ByOrderByRegDateDesc();

//    PageResultDTO<PromotionBoardDTO,Object[]> getList(PageRequestDTO pageRequestDTO);

    default Promotion dtoToEntity (PromotionBoardDTO promotionBoardDTO) {

        Sports sports = Sports.builder()
                .id(Long.valueOf(promotionBoardDTO.getSports()))
                .build();

        User user = User.builder()
                .id(Long.valueOf(promotionBoardDTO.getWriter()))
                .build();

        Promotion promotion = Promotion.builder()
                .id(promotionBoardDTO.getId())
                .title(promotionBoardDTO.getTitle())
                .content(promotionBoardDTO.getContent())
                .visitCount(promotionBoardDTO.getReplyCount())
                //.club(club)
                .writer(user)
                .build();

        return promotion;
    }

    default PromotionBoardDTO entityToDto (Promotion promotion, Sports sports, User user, Long replyCount) {

        PromotionBoardDTO promotionBoardDTO = PromotionBoardDTO.builder()
                .id(promotion.getId())
                .title(promotion.getTitle())
                .content(promotion.getContent())
                .sports(sports.getName())
                .writer(user.getNickname())
                .visitCount((long) promotion.getVisitCount())
                .regDate(promotion.getRegDate())
                .modDate(promotion.getModDate())
                .replyCount(replyCount.intValue())
                .build();

        return promotionBoardDTO;
    }

}
