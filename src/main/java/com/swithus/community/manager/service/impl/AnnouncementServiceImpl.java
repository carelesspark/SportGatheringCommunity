package com.swithus.community.manager.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.PageRequestDTO;
import com.swithus.community.manager.dto.PageResultDTO;
import com.swithus.community.manager.entity.Announcement;
import com.swithus.community.manager.repository.AnnouncementRepository;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;


    @Override
    public PageResultDTO<AnnouncementDTO, Announcement> getAnnouncementList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Announcement> result = announcementRepository.findAll(pageable);

        Function<Announcement, AnnouncementDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO(result, fn);
    }

    @Override
    public Long write(AnnouncementDTO dto) {

        Announcement entity = dtoTOEntity(dto);
        announcementRepository.save(entity);

        return entity.getId();
    }
}
