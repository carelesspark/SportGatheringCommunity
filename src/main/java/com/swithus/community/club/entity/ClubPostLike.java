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
public class ClubPostLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 게시글 키
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubPost post;
    // 누른 사람 키
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember member;
}
