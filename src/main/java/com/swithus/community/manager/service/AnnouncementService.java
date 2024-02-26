package com.swithus.community.manager.service;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.page.AncPageRequestDTO;
import com.swithus.community.manager.dto.page.AncPageResultDTO;
import com.swithus.community.manager.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    AncPageResultDTO<AnnouncementDTO, Announcement> getAnnouncementList(AncPageRequestDTO requestDTO);

    Long write(AnnouncementDTO dto);

    AnnouncementDTO info(Long no);

    void modify(AnnouncementDTO dto);

    void delete(Long no);

    List<Announcement> findTop4ByOrderByRegDateDesc();

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
