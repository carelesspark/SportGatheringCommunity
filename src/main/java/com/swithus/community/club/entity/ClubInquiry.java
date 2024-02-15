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
public class ClubInquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 문의 헤더 키
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubInquiryHeader header;

    // 작성자
    private byte writer;
    // 내용
    private String comment;
    // 읽기 여부
    private byte hasRead;
}
