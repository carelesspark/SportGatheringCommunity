package com.swithus.community.club.dto;

import com.swithus.community.global.dto.ImageDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClubPostDTO {
    private Long clubPostId;

    // Club
    private Long clubId;

    // ClubPostCtgr
    private Long ctgrId;
    private String ctgrName;

    // ClubMember
    private Long writerId;
    private String writerName;

    // ClubPost 내용물
    private String title;
    private String content;
    private int visitCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // 셈한 것들
    private Long replyCount;
    private Long likeCount;
    // 자기 자신이 이 포스트에 좋아요를 눌렀나를 확인하는 변수
    private boolean clicked;

    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
}
