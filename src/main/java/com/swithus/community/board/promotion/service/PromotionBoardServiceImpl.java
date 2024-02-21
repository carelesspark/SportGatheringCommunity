package com.swithus.community.board.promotion.service;

import com.swithus.community.board.promotion.repository.PromotionBoardRepository;
import com.swithus.community.board.promotion.repository.PromotionReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class PromotionBoardServiceImpl implements PromotionBoardService{

    private final PromotionBoardRepository promotionBoardRepository;
    private final PromotionReplyRepository promotionReplyRepository;

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
