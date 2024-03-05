package com.swithus.community.board.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.dto.page.PageResultDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.entity.QPromotion;
import com.swithus.community.board.repository.PromotionBoardRepository;
import com.swithus.community.board.repository.PromotionReviewRepository;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.ClubRepository;
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
public class PromotionBoardServiceImpl implements PromotionBoardService {

    private final PromotionBoardRepository promotionBoardRepository;
    private final PromotionReviewRepository promotionReplyRepository;
    private final ClubRepository clubRepository;
    private final UserDetailRepository userDetailRepository;

    @Override
    public List<Promotion> findTop4ByOrderByRegDateDesc() {
        return promotionBoardRepository.findTop4ByOrderByRegDateDesc();
    }

    @Override
    public boolean checkClubLeader(String nickname) {
        List<Club> result = clubRepository.findByUserNickname(nickname);
        if (result.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public PageResultDTO<PromotionBoardDTO, Promotion> getPromotionList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Promotion> result = promotionBoardRepository.findAll(booleanBuilder, pageable);

        Function<Promotion, PromotionBoardDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO(result, fn);
    }


    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QPromotion qPromotion = QPromotion.promotion;

        String search = pageRequestDTO.getSearch();
        BooleanExpression expression = qPromotion.id.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("sports")) {
            conditionBuilder.or(qPromotion.club.sports.name.contains(search));
        }

        if (type.contains("t")) {
            conditionBuilder.or(qPromotion.title.contains(search));
        }

        if (type.contains("c")) {
            conditionBuilder.or(qPromotion.content.contains(search));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public void write(PromotionBoardDTO dto) {

        Optional<Club> resultClub = clubRepository.findById(dto.getClubId());
        Club club = resultClub.get();

        Optional<User> resultUser = userDetailRepository.findById(dto.getUserId());
        User user = resultUser.get();


        Promotion entity = Promotion.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .title(dto.getTitle())
                .visitCount(0)
                .club(club)
                .writer(user)
                .build();

        promotionBoardRepository.save(entity);
    }

    @Override
    public PromotionBoardDTO info(Long no) {
        Optional<Promotion> info = promotionBoardRepository.findById(no);

        if (info.isPresent()) {
            Promotion entity = info.get();
            entity.plusVisitCount(entity.getVisitCount());

            promotionBoardRepository.save(entity);
        }

        return info.isPresent() ? entityToDto(info.get()) : null;
    }

    @Override
    public void modify(PromotionBoardDTO dto) {
        Optional<Promotion> before_modify = promotionBoardRepository.findById(dto.getId());

        if (before_modify.isPresent()) {
            Promotion entity = before_modify.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            promotionBoardRepository.save(entity);
        }
    }

    @Override
    public void deletePromotion(Long no) {
        promotionBoardRepository.deleteById(no);
    }


}
