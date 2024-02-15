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
public class GreetingsLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 가입인사 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Greetings greetings;
    // 누른 사람 키
    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember member;
}
