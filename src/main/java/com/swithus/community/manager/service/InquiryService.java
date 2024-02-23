package com.swithus.community.manager.service;

import com.swithus.community.board.entity.Inquiry;
import com.swithus.community.board.entity.InquiryAnswer;
import com.swithus.community.manager.dto.InquiryAnswerDTO;
import com.swithus.community.manager.dto.InquiryDTO;
import com.swithus.community.manager.dto.page.MainPageRequestDTO;
import com.swithus.community.manager.dto.page.MainPageResultDTO;
import com.swithus.community.manager.repository.InquiryAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface InquiryService {

    MainPageResultDTO<InquiryDTO, Inquiry>getInquiryList(MainPageRequestDTO mainPageRequestDTO);

    InquiryDTO inquiryInfo(Long id);

    void inquiryAnswer(InquiryAnswerDTO inquiryAnswerDTO);

    InquiryAnswerDTO inquiryAnswerInfo(Long id);

    default InquiryAnswer dtoToEntity(InquiryAnswerDTO inquiryAnswerDTO){
        InquiryAnswer entity = InquiryAnswer.builder()
                .id(inquiryAnswerDTO.getId())
                .title(inquiryAnswerDTO.getTitle())
                .content(inquiryAnswerDTO.getContent())
                .inquiry(Inquiry.builder().id(inquiryAnswerDTO.getInquiryId()).build())
                .build();

        return entity;
    }

    default InquiryAnswerDTO entityToAnswerDto(InquiryAnswer inquiryAnswer){
        InquiryAnswerDTO dto = InquiryAnswerDTO.builder()
                .id(inquiryAnswer.getId())
                .regDate(inquiryAnswer.getRegDate())
                .title(inquiryAnswer.getTitle())
                .content(inquiryAnswer.getContent())
                .isAnswered(inquiryAnswer.getInquiry().isAnswered())
                .build();

        return dto;
    }
}
