package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class Meeting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 클럽 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;
    // 카테고리 키
    @ManyToOne(fetch = FetchType.LAZY)
    private MeetingCtgr ctgr;

    // 날짜
    private LocalDateTime when;
    // 위치
    private String where;
    // 설명
    private String what;
    // 인원
    private int who;
}
