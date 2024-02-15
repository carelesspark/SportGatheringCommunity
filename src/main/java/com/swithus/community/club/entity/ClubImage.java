package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import lombok.*;

//@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Table
@ToString
public class ClubImage extends BaseEntity {

    private Long key;
    // 클럽 키
    private Club club;

    // 이름
    private String name;
    // 경로
    private String path;
    // uuid
    private String uuid;
}
