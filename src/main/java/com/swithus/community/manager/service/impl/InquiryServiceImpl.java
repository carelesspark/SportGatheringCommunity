package com.swithus.community.manager.service.impl;


import com.swithus.community.board.entity.Inquiry;
import com.swithus.community.board.entity.InquiryAnswer;
import com.swithus.community.manager.dto.InquiryAnswerDTO;
import com.swithus.community.manager.dto.InquiryDTO;
import com.swithus.community.manager.dto.page.MainPageRequestDTO;
import com.swithus.community.manager.dto.page.MainPageResultDTO;
import com.swithus.community.manager.repository.InquiryAnswerRepository;
import com.swithus.community.manager.repository.InquiryRepository;
import com.swithus.community.manager.service.InquiryService;
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
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;

    private InquiryDTO entityToDto(Inquiry inquiry) {
        Long answerId = inquiryAnswerRepository.findAnswerIdByInquiryId(inquiry.getId());

        return InquiryDTO.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .writer(inquiry.getUser().getNickname())
                .regDate(inquiry.getRegDate())
                .isAnswered(inquiry.isAnswered())
                .content(inquiry.getContent())
                .ctgr(inquiry.getCtgr().getName())
                .answerId(answerId)
                .build();
    }

    @Override
    public MainPageResultDTO<InquiryDTO, Inquiry> getInquiryList(MainPageRequestDTO mainPageRequestDTO) {
        Pageable pageable = mainPageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Inquiry> result = inquiryRepository.findAll(pageable);

        Function<Inquiry, InquiryDTO> fn = (entity -> entityToDto(entity));

        return new MainPageResultDTO(result, fn);

    }
    @Override
    public InquiryDTO inquiryInfo(Long id) {
        Optional<Inquiry> result = inquiryRepository.findById(id);

        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    public void inquiryAnswer(InquiryAnswerDTO inquiryAnswerDTO) {

        InquiryAnswer entity = dtoToEntity(inquiryAnswerDTO);
        inquiryAnswerRepository.save(entity);
        Optional<Inquiry> before_modify = inquiryRepository.findById(inquiryAnswerDTO.getInquiryId());
        if(before_modify.isPresent()){
            Inquiry inquiry = before_modify.get();

            inquiry.change_isAnswered(true);
            inquiryRepository.save(inquiry);
        }

    }

    @Override
    public InquiryAnswerDTO inquiryAnswerInfo(Long id) {
        Optional<InquiryAnswer> result = inquiryAnswerRepository.findById(id);

        return result.isPresent()? entityToAnswerDto(result.get()) : null;
    }


}
