package com.swithus.community.manager.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.dto.page.FaqPageResultDTO;
import com.swithus.community.manager.entity.Faq;
import com.swithus.community.manager.entity.FaqCtgr;
import com.swithus.community.manager.entity.QFaq;
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

        Page<Faq> result = faqRepository.findAll(pageable);

        Function<Faq, FaqDTO> fn = (entity -> entityToDto(entity));

        return new FaqPageResultDTO(result, fn);
    }

    @Override
    public FaqDTO info(Long id) {
        Optional<Faq> result = faqRepository.findById(id);

        return result.isPresent()? entityToDto(result.get()) : null;
    }


}
