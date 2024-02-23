package com.swithus.community.club.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
public class PostPageRequestDTO {
    // 사용자에게 요청받은 페이지 번호
    private int page;
    // 사용자에게 요청받은 페이지당 객체 수
    private int size;

    private Long ctgrId;
    private String type;
    private String keyword;

    public PostPageRequestDTO() {
        page = 1;
        size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
