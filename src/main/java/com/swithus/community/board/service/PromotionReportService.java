package com.swithus.community.board.service;


import com.swithus.community.board.dto.ReportPostDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.manager.dto.page.ReportedPageRequestDTO;
import com.swithus.community.manager.dto.page.ReportedPageResultDTO;
import com.swithus.community.manager.entity.ReportPost;
import com.swithus.community.manager.entity.ReportPostCtgr;

public interface PromotionReportService {

    ReportedPageResultDTO<ReportPostDTO, ReportPost> getReportedPostList(ReportedPageRequestDTO requestDTO);

    void report(ReportPostDTO dto);

    Long count(ReportPostDTO dto);

    Long countReportedPost(Long postId);

    void deletePromotion(Long postId, Long id);

    void isUnsuitabled(Long id);

    ReportPostDTO info(Long no);



    default ReportPost dtoToEntity(ReportPostDTO dto){

        ReportPost entity = ReportPost.builder()
                .id(dto.getId())
                .reason(dto.getReason())
                .nickname(dto.getNickname())
                .ctgr(ReportPostCtgr.builder().id(dto.getCtgrId()).build())
                .postId(dto.getPostId())
                .postContent(dto.getPostContent())
                .postWriter(dto.getPostWriter())
                .isSolved(dto.isSolved())
                .isSuitabled(dto.isSuitabled())
                .build();
        return entity;
    }

    default ReportPostDTO entityToDto(ReportPost reportPost){

        ReportPostDTO dto = ReportPostDTO.builder()
                .id(reportPost.getId())
                .modDate(reportPost.getModDate())
                .regDate(reportPost.getRegDate())
                .nickname(reportPost.getNickname())
                .ctgrId(reportPost.getCtgr().getId())
                .ctgrType(reportPost.getCtgr().getCtgrType())
                .reason(reportPost.getReason())
                .postWriter(reportPost.getPostWriter())
                .postId(reportPost.getPostId())
                .isSolved(reportPost.isSolved())
                .isSuitabled(reportPost.isSuitabled())
                .postContent(reportPost.getPostContent())
                .build();

        return dto;
    }
}
