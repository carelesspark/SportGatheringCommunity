package com.swithus.community.board.service;

import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.board.dto.ReportPostDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.manager.entity.ReportPost;
import com.swithus.community.manager.entity.ReportPostCtgr;

public interface PromotionReportService {

    void report(ReportPostDTO dto);

    Long count(ReportPostDTO dto);

    default ReportPost dtoToEntity(ReportPostDTO dto){

        ReportPost entity = ReportPost.builder()
                .id(dto.getId())
                .reason(dto.getReason())
                .nickname(dto.getNickname())
                .ctgr(ReportPostCtgr.builder().id(dto.getCtgrId()).build())
                .post(Promotion.builder().id(dto.getPostId()).build())
                .build();
        return entity;
    }
}
