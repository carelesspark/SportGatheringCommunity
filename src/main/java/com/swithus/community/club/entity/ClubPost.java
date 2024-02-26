package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class ClubPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubPostCtgr ctgr;
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;
    // 작성자 키
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember writer;

    // 제목
    private String title;
    // 내용
    private String content;
    // 조회수
    private int visitCount;
}
