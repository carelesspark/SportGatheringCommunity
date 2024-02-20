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
public class GreetingsPageRequestDTO {
    // 사용자에게 요청받은 페이지 번호
    private int page;
    // 사용자에게 요청받은 페이지당 객체 수
    private int size;
    // 해당 클럽의 가입인사만을 보여주기위한 변수
    private Long clubId;
    // 내가 쓴 가입인사를 찾기 위한 변수
    private Long userId;

    public GreetingsPageRequestDTO() {
        page = 1;
        size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
