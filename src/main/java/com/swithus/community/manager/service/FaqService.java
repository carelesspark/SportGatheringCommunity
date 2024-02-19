package com.swithus.community.manager.service;

import com.swithus.community.manager.dto.FaqCtgrDTO;
import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.dto.page.FaqPageResultDTO;
import com.swithus.community.manager.entity.Faq;
import com.swithus.community.manager.entity.FaqCtgr;


public interface FaqService {

    Long write(FaqDTO dto);

    FaqPageResultDTO<FaqDTO, Faq> getFaqList(FaqPageRequestDTO requestDTO);

    FaqDTO info(Long id);

    void modify(FaqDTO dto);

    void delete(Long id);


    default Faq dtoToEntity(FaqDTO faqDTO){

        Faq faq = Faq.builder()
                .id(faqDTO.getId())
                .answer(faqDTO.getAnswer())
                .question(faqDTO.getQuestion())
                .ctgr(FaqCtgr.builder().id(faqDTO.getCtgrId()).build())
                .build();
        return faq;
    }

    default FaqDTO entityToDto(Faq faq) {
        FaqDTO dto = FaqDTO.builder()
                .id(faq.getId())
                .modDate(faq.getModDate())
                .regDate(faq.getRegDate())
                .answer(faq.getAnswer())
                .question(faq.getQuestion())
                .ctgrId(faq.getCtgr().getId())
                .ctgrType(faq.getCtgr().getCtgrType())
                .build();

            return dto;
    }
}
