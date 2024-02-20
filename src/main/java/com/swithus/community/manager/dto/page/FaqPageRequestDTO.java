package com.swithus.community.manager.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class FaqPageRequestDTO {
    private int page;

    private int size;

    private String type;

    private String category;

    public FaqPageRequestDTO(){
        this.page = 1;
        this.size = 5;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}
