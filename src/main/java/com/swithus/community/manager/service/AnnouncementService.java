package com.swithus.community.manager.service;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.entity.Announcement;

public interface AnnouncementService {



    default Announcement dtoTOEntity(AnnouncementDTO dto){
        Announcement entity = Announcement.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .title(dto.getTitle())
                .visit_count(dto.getVisit_count())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default AnnouncementDTO entityToDto(AnnouncementDTO entity){
        AnnouncementDTO dto = AnnouncementDTO.builder()
                .id(entity.getId())
                .mod_date(entity.getMod_date())
                .reg_Date(entity.getReg_Date())
                .content(entity.getContent())
                .title(entity.getTitle())
                .visit_count(entity.getVisit_count())
                .writer(entity.getWriter()).build();

        return dto;
    }
}
