package com.swithus.community.club.dto;

import com.swithus.community.global.dto.ImageDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GreetingsDTO {
    // 가입인사 키
    private Long greetingsId;
    // 클럽회원 이름
    private String memberName;
    // 가입인사말
    private String content;
    // 이미지 1장
    private ImageDTO imageDTO;
    // 좋아요 개수
    private Long likeCount;
    // 본 유저가 하트를 눌렀나?
    private boolean clicked;
}
