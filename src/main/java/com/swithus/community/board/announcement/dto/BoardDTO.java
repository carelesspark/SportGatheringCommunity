package com.swithus.community.board.announcement.dto;

import lombok.*;

import java.time.LocalDateTime;



public class BoardDTO {

    private Long bno;

    private String title;

    private String writer;

    private Long visitCount;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
