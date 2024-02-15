package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class Club extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 클럽장 키
    // @ManyToOne(fetch = FetchType.LAZY)
    // private User leader;

    // 지역 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;
    // 종목 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Sports sports;

    // 클럽명
    private String name;
    // 클럽 소개
    private String introduce;
    // 클럽 랭크
    private int rank;
    // 클럽 포인트
    private int point;
}
