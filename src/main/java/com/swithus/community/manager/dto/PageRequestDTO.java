package com.swithus.community.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;

    private int size;

    private String type;

    private String keyword;

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }


}
