package com.swithus.community.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.board.dto.ReportPostDTO;
import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.dto.page.PageResultDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.entity.QPromotion;
import com.swithus.community.board.repository.PromotionBoardRepository;
import com.swithus.community.board.repository.PromotionReviewRepository;
import com.swithus.community.board.repository.ReportPostRepository;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.ClubRepository;
import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.page.AncPageResultDTO;
import com.swithus.community.manager.dto.page.ReportedPageRequestDTO;
import com.swithus.community.manager.dto.page.ReportedPageResultDTO;
import com.swithus.community.manager.entity.Announcement;
import com.swithus.community.manager.entity.ReportPost;
import com.swithus.community.manager.repository.UserDetailRepository;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
@Log4j2
public class PromotionReportServiceImpl implements PromotionReportService {
    private final ReportPostRepository reportPostRepository;
    private final PromotionBoardRepository promotionBoardRepository;

    @Override
    public ReportedPageResultDTO<ReportPostDTO, ReportPost> getReportedPostList(ReportedPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<ReportPost> result = reportPostRepository.findAll(pageable);

        Function<ReportPost, ReportPostDTO> fn = (entity -> entityToDto(entity));

        return new ReportedPageResultDTO(result, fn);
    }

    @Override
    public void report(ReportPostDTO dto) {
        ReportPost entity = dtoToEntity(dto);
        reportPostRepository.save(entity);

    }

    @Override
    public Long count(ReportPostDTO dto) {

        return reportPostRepository.countByNicknameAndPostId(dto.getNickname(), dto.getPostId());
    }

    @Override
    public Long countReportedPost(Long postId) {

        return reportPostRepository.countByPostId(postId);
    }

    @Override
    public void deletePromotion(Long postId, Long id) {
        promotionBoardRepository.deleteById(postId);

        Optional<ReportPost> before_info = reportPostRepository.findById(id);
        if(before_info.isPresent()){
            ReportPost reportPost = before_info.get();
            reportPost.change_isSolved(true);
            reportPost.change_isSuitabled(true);
            reportPostRepository.save(reportPost);
        }
    }

    @Override
    public void isUnsuitabled(Long id) {
        Optional<ReportPost> before_info = reportPostRepository.findById(id);
        if(before_info.isPresent()){
            ReportPost reportPost = before_info.get();
            reportPost.change_isSolved(true);
            reportPostRepository.save(reportPost);
        }
    }

    @Override
    public ReportPostDTO info(Long no) {
        Optional<ReportPost> report_info = reportPostRepository.findById(no);

        return report_info.isPresent() ? entityToDto(report_info.get()) : null;
    }
}
