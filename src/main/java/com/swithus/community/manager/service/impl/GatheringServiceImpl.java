package com.swithus.community.manager.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.QClub;
import com.swithus.community.manager.dto.GatheringDTO;
import com.swithus.community.manager.dto.WithdrawalGatheringDTO;
import com.swithus.community.manager.dto.page.GatheringPageRequestDTO;
import com.swithus.community.manager.dto.page.GatheringPageResultDTO;
import com.swithus.community.manager.entity.QWithdrawalGathering;
import com.swithus.community.manager.entity.WithdrawalGathering;
import com.swithus.community.manager.repository.GatheringRepository;
import com.swithus.community.manager.repository.WithdrawalGatheringRepository;
import com.swithus.community.manager.service.GatheringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {


    private final GatheringRepository gatheringRepository;
    private final WithdrawalGatheringRepository withdrawalGatheringRepository;


    @Override
    public Long countGathering() {
        return gatheringRepository.countBy();
    }

    @Override
    public GatheringPageResultDTO<GatheringDTO, Club> getGatheringList(GatheringPageRequestDTO gatheringPageRequestDTO) {
        Pageable pageable = gatheringPageRequestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(gatheringPageRequestDTO);

        Page<Club> result = gatheringRepository.findAll(booleanBuilder, pageable);

        Function<Club, GatheringDTO> fn = (entity -> entityToDto(entity));

        return new GatheringPageResultDTO(result, fn);
    }

    private BooleanBuilder getSearch(GatheringPageRequestDTO requestDTO){
        String search = requestDTO.getSearch();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QClub qClub = QClub.club;

        BooleanExpression expression = qClub.id.gt(0L);
        booleanBuilder.and(expression);

        if(search == null || search.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.and(qClub.name.contains(search));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public GatheringDTO info(long id) {
        Optional<Club> result = gatheringRepository.findById(id);

        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    public void withdrawalGathering(WithdrawalGatheringDTO withdrawalGatheringDTO) {
        WithdrawalGathering entity = dtoToEntity(withdrawalGatheringDTO);
        withdrawalGatheringRepository.save(entity);
        gatheringRepository.deleteById(withdrawalGatheringDTO.getClubId());
    }

    @Override
    public GatheringPageResultDTO<WithdrawalGatheringDTO, WithdrawalGathering> getDeletedGatheringList(GatheringPageRequestDTO gatheringPageRequestDTO) {
        Pageable pageable = gatheringPageRequestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearchDeletedGathering(gatheringPageRequestDTO);

        Page<WithdrawalGathering> result = withdrawalGatheringRepository.findAll(booleanBuilder, pageable);

        Function<WithdrawalGathering, WithdrawalGatheringDTO> fn = (entity -> entityToWithdrawalGatheringDTO(entity));

        return new GatheringPageResultDTO(result, fn);
    }


    private BooleanBuilder getSearchDeletedGathering(GatheringPageRequestDTO requestDTO){
        String search = requestDTO.getSearch();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QWithdrawalGathering qWithdrawalGathering = QWithdrawalGathering.withdrawalGathering;

        BooleanExpression expression = qWithdrawalGathering.id.gt(0L);
        booleanBuilder.and(expression);

        if(search == null || search.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        conditionBuilder.or(qWithdrawalGathering.name.contains(search));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public WithdrawalGatheringDTO infoDeletedGathering(long no) {
        Optional<WithdrawalGathering> result = withdrawalGatheringRepository.findById(no);

        return result.isPresent()? entityToWithdrawalGatheringDTO(result.get()) : null;
    }


}


