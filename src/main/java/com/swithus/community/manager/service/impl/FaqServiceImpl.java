package com.swithus.community.manager.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.dto.page.FaqPageResultDTO;
import com.swithus.community.manager.entity.Faq;
import com.swithus.community.manager.entity.FaqCtgr;
import com.swithus.community.manager.entity.QFaq;
import com.swithus.community.manager.entity.QFaqCtgr;
import com.swithus.community.manager.repository.FaqCtgrRepository;
import com.swithus.community.manager.repository.FaqRepository;
import com.swithus.community.manager.service.FaqService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.function.Function;


@Service
@Log4j2
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final FaqCtgrRepository faqCtgrRepository;

    @Override
    @Transactional
    public Long write(FaqDTO dto) {

        FaqCtgr faqCtgr = faqCtgrRepository.getOne(dto.getCtgrId());

        Faq faq = Faq.builder()
                .id(dto.getId())
                .answer(dto.getAnswer())
                .question(dto.getQuestion())
                .ctgr(faqCtgr)
                .build();

        faqRepository.save(faq);

        return faq.getId();
    }

    @Override
    public FaqPageResultDTO<FaqDTO, Faq> getFaqList(FaqPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Faq> result = faqRepository.findAll(booleanBuilder, pageable);

        Function<Faq, FaqDTO> fn = (entity -> entityToDto(entity));

        return new FaqPageResultDTO(result, fn);
    }

    private BooleanBuilder getSearch(FaqPageRequestDTO pageRequestDTO){
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QFaq qFaq = QFaq.faq;

        BooleanExpression expression = qFaq.id.gt(0L);
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t1")){
            conditionBuilder.or(qFaq.ctgr.ctgrType.contains("사이트 이용"));
        }

        if(type.contains("t2")){
            conditionBuilder.or(qFaq.ctgr.ctgrType.contains("물품 나눔"));
        }

        if(type.contains("t3")){
            conditionBuilder.or(qFaq.ctgr.ctgrType.contains("소모임"));
        }

        if(type.contains("t4")){
            conditionBuilder.or(qFaq.ctgr.ctgrType.contains("대회"));
        }

        if(type.contains("t5")){
            conditionBuilder.or(qFaq.ctgr.ctgrType.contains("기타"));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public FaqDTO info(Long id) {
        Optional<Faq> result = faqRepository.findById(id);

        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    @Transactional
    public void modify(FaqDTO dto) {
        Optional<Faq> before_modify = faqRepository.findById(dto.getId());

        if(before_modify.isPresent()){
            Faq entity = before_modify.get();
            entity.change_question(dto.getQuestion());
            entity.change_answer(dto.getAnswer());

            FaqCtgr faqCtgr = faqCtgrRepository.getOne(dto.getCtgrId());
            entity.change_ctgr(faqCtgr);

            faqRepository.save(entity);
        }


    }

    @Override
    public void delete(Long id) {
        faqRepository.deleteById(id);
    }


}
