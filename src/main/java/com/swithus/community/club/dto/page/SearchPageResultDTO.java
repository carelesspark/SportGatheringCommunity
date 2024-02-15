package com.swithus.community.club.dto.page;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

// 여기서 R은 DTO, T는 Entity 타입을 말함
@Data
public class SearchPageResultDTO<DTO, T> {
    // 현재 페이지의 객체들을 담은 리스트
    private List<DTO> clubDTOList;
    // 전체 데이터 수
    private int totalPage;
    // 현재 페이지 번호
    private int page;
    // 한 페이지당 보여주는 데이터 수
    private int size;
    // 시작 페이지 번호
    private int start;
    // 마지막 페이지 번호
    private int end;
    // 이전 페이지 존재 여부
    private boolean prev;
    // 다음 페이지 존재 여부
    private boolean next;
    // 페이지간 이동을 도와주는 네비게이션 바를 만들기 위한 페이지 번호 리스트
    private List<Integer> pageList;

    // Page<T>, Function<T, R> → T 타입을 R 타입으로 바꿔주는 함수
    public SearchPageResultDTO(Page<T> result, Function<T, DTO> func) {
        clubDTOList = result.stream().map(func).toList();
        totalPage = result.getTotalPages();

        Pageable pageable = result.getPageable();
        page = pageable.getPageNumber() + 1;
        size = pageable.getPageSize();

        if (page <= 6) start = 1;
        else if (page >= totalPage - 4) start = Math.max(1, totalPage - 9);
        else start = page - 5;
        end = Math.min(start + 9, totalPage);

        prev = start > 1;
        next = totalPage > end;

        // boxed(): IntStream → Stream<Integer> → List<Integer>
        pageList = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
