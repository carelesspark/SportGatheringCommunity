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
public class Greetings extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 멤버 키
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember member;
    
    // 내용
    private String content;
}
