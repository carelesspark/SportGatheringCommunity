package com.swithus.community.manager.service;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.PageRequestDTO;
import com.swithus.community.manager.dto.PageResultDTO;
import com.swithus.community.manager.entity.Announcement;

public interface AnnouncementService {

    PageResultDTO<AnnouncementDTO, Announcement> getAnnouncementList(PageRequestDTO requestDTO);

    Long write(AnnouncementDTO dto);

    AnnouncementDTO info(Long no);
    default Announcement dtoTOEntity(AnnouncementDTO dto){
        Announcement entity = Announcement.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .title(dto.getTitle())
                .visitCount(dto.getVisitCount())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default AnnouncementDTO entityToDto(Announcement entity){
        AnnouncementDTO dto = AnnouncementDTO.builder()
                .id(entity.getId())
                .modDate(entity.getModDate())
                .regDate(entity.getRegDate())
                .content(entity.getContent())
                .title(entity.getTitle())
                .visitCount(entity.getVisitCount())
                .writer(entity.getWriter()).build();

        return dto;
    }
}
