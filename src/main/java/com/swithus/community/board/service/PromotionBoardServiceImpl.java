package com.swithus.community.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.dto.page.PageResultDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.entity.QPromotion;
import com.swithus.community.board.repository.PromotionBoardRepository;
import com.swithus.community.board.repository.PromotionReplyRepository;
import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.dto.page.FaqPageResultDTO;
import com.swithus.community.manager.entity.Faq;
import com.swithus.community.manager.entity.QFaq;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
@Log4j2
public class PromotionBoardServiceImpl implements PromotionBoardService {

    private final PromotionBoardRepository promotionBoardRepository;
    private final PromotionReplyRepository promotionReplyRepository;

    @Override
    public List<Promotion> findTop4ByOrderByRegDateDesc() {
        return promotionBoardRepository.findTop4ByOrderByRegDateDesc();
    }

    @Override
    public PageResultDTO<PromotionBoardDTO, Promotion> getPromotionList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Promotion> result = promotionBoardRepository.findAll(booleanBuilder, pageable);

        Function<Promotion, PromotionBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO){
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QPromotion qPromotion = QPromotion.promotion;

        String search = pageRequestDTO.getSearch();
        BooleanExpression expression = qPromotion.id.gt(0L);
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("sports")){
            conditionBuilder.or(qPromotion.club.sports.name.contains(search));
        }

        if(type.contains("t")){
            conditionBuilder.or(qPromotion.title.contains(search));
        }

        if(type.contains("c")){
            conditionBuilder.or(qPromotion.content.contains(search));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

//    @Override
//    public PageResultDTO<PromotionBoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//
//        log.info(pageRequestDTO);
//
//        Function<Object[],PromotionBoardDTO> fn = (en -> entityToDto((Promotion) en[0],(Club) en[1],(User) en[2],(Long) en[3]));
//
//        Page<Object[]> result = promotionBoardRepository.getBoardWithReplyCount(pageRequestDTO.
//                getPageable(Sort.by("id").descending()));
//
//        return new PageResultDTO<>(result,fn);
//    }

}
